package core.file.algs.asymmetric;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import core.file.algs.BaseAlgo;

public class RSA extends BaseAlgo {

	private static final String ALGORITHM = "RSA";
	private final int KEY_SIZE;
	private final Cipher CIPHER;

	public RSA(Key key, int keySize, boolean encryptMode)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		this.KEY_SIZE = keySize;
		this.CIPHER = Cipher.getInstance(RSA.ALGORITHM);
		this.CIPHER.init(encryptMode ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key);
	}

	public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA.ALGORITHM);
		keyPairGenerator.initialize(keySize);
		return keyPairGenerator.genKeyPair();
	}

	public int getKeySize() {
		return this.KEY_SIZE;
	}

	@Override
	public int blockSize() {
		return this.outputBlockSize(this.KEY_SIZE) - 11; // 11 bytes paddings
	}

	@Override
	public int outputBlockSize(int blockSize) {
		return this.KEY_SIZE / 8;
	}

	@Override
	public byte[] doFinal(byte[] data) throws IllegalBlockSizeException, BadPaddingException {
		return this.CIPHER.doFinal(data);
	}
}
