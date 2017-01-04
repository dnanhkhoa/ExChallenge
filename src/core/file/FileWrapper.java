package core.file;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import exception.ExceptionInfo;

// Done

public final class FileWrapper implements AutoCloseable {

	private final static String SIGNATURE = "EXCHALLENGE";

	private final OutputStream outputStream;
	private final DataOutputStream dataOutputStream;

	public FileWrapper(File outFile, FileMeta fileMeta) throws ExceptionInfo, IOException {
		this.outputStream = new FileOutputStream(outFile);
		this.dataOutputStream = new DataOutputStream(outputStream);

		byte[] metaData = null;

		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
				objectOutputStream.writeObject(fileMeta);
				objectOutputStream.flush();
				metaData = byteArrayOutputStream.toByteArray();
			}
		}

		if (metaData == null) {
			throw new ExceptionInfo("Can not wrap this file!");
		}

		this.dataOutputStream.write(SIGNATURE.getBytes());
		this.dataOutputStream.writeInt(metaData.length);
		this.dataOutputStream.write(metaData);
	}

	public void write(byte[] data) throws IOException {
		this.dataOutputStream.write(data);
	}

	public void write(byte[] data, int offset, int length) throws IOException {
		this.dataOutputStream.write(data, offset, length);
	}

	@Override
	public void close() throws Exception {
		try {
			this.dataOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
