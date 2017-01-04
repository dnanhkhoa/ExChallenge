package core.file;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import exception.ExceptionInfo;

public final class FileUnwrapper implements AutoCloseable {

	private final static String SIGNATURE = "EXCHALLENGE";

	private final InputStream inputStream;
	private final DataInputStream dataInputStream;
	private FileMeta fileMeta = null;

	public FileUnwrapper(File inFile) throws IOException, ExceptionInfo, ClassNotFoundException {
		this.inputStream = new FileInputStream(inFile);
		this.dataInputStream = new DataInputStream(inputStream);

		byte[] signatureData = new byte[SIGNATURE.length()];
		if (this.dataInputStream.read(signatureData) != SIGNATURE.length()
				|| !SIGNATURE.equals(new String(signatureData))) {
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
