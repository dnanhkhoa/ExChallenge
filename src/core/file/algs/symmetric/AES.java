package core.file.algs.symmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import core.file.algs.BaseAlgo;

public final class AES extends BaseAlgo {

	private final Cipher CIPHER;

	public AES() {
	}

	@Override
	public int blockSize() {
		return super.blockSize();
	}

	@Override
	public int encryptedBlockSize() {
		return super.encryptedBlockSize();
	}

	@Override
	public byte[] doFinal(byte[] data) throws IllegalBlockSizeException, BadPaddingException {
		return this.CIPHER.doFinal(data);
	}

}
