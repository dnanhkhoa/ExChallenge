package core.file.algs;

// Done

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class BaseAlgo {

	protected static final int DEFAULT_BLOCK_SIZE = 512;

	public BaseAlgo() {
	}

	public int blockSize() {
		return DEFAULT_BLOCK_SIZE;
	}

	public int outputBlockSize() {
		return DEFAULT_BLOCK_SIZE;
	}

	public byte[] doFinal(byte[] data) throws IllegalBlockSizeException, BadPaddingException {
		return data;
	}
}
