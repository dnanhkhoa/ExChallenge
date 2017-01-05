package core.file;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import exception.ExceptionInfo;

// Done

public final class FileUnwrapper implements AutoCloseable {

	private String signature;

	private final InputStream inputStream;
	private final DataInputStream dataInputStream;
	private FileMeta fileMeta = null;

	public FileUnwrapper(File inFile) throws IOException, ExceptionInfo, ClassNotFoundException {
		this(inFile, "EXCHALLENGE");
	}

	public FileUnwrapper(File inFile, String signature) throws IOException, ExceptionInfo, ClassNotFoundException {
		this.signature = signature;

		this.inputStream = new FileInputStream(inFile);
		this.dataInputStream = new DataInputStream(inputStream);

		byte[] signatureData = new byte[this.signature.length()];
		if (this.dataInputStream.read(signatureData) != this.signature.length()
				|| !this.signature.equals(new String(signatureData))) {
			throw new ExceptionInfo("File is invalid!");
		}

		int fileMetaSize = dataInputStream.readInt();
		byte[] fileMetaData = new byte[fileMetaSize];

		if (dataInputStream.read(fileMetaData) == fileMetaSize) {
			try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileMetaData)) {
				try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
					this.fileMeta = (FileMeta) objectInputStream.readObject();
				}
			}
		}
	}

	public FileMeta readFileMeta() {
		return this.fileMeta;
	}

	public int read(byte[] buffer) throws IOException {
		return this.dataInputStream.read(buffer);
	}

	public int read(byte[] buffer, int offset, int length) throws IOException {
		return this.dataInputStream.read(buffer, offset, length);
	}

	@Override
	public void close() throws Exception {
		try {
			this.dataInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
