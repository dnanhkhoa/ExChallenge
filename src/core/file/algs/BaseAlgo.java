package core.file.algs;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class BaseAlgo {

	private static final int DEFAULT_BLOCK_SIZE = 512;

	public BaseAlgo() {
	}

	public int blockSize() {
		return DEFAULT_BLOCK_SIZE;
	}

	public int encryptedBlockSize() {
		return DEFAULT_BLOCK_SIZE;
	}

	public byte[] doFinal(byte[] data) throws IllegalBlockSizeException, BadPaddingException {
		return data;
	}
}
