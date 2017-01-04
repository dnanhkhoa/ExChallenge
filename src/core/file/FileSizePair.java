package core.file;

import java.io.Serializable;

// Done

public class FileSizePair implements Serializable {

	private static final long serialVersionUID = 1L;

	private String absolutePath;
	private String relativePath;
	private long size;

	public FileSizePair(String absolutePath, String relativePath, long size) {
		this.absolutePath = absolutePath;
		this.relativePath = relativePath;
		this.size = size;
	}

	public String getAbsolutePath() {
		return this.absolutePath;
	}

	public String getRelativePath() {
		return this.relativePath;
	}

	public long getSize() {
		return this.size;
	}
}
