package core.file.algs.symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import core.file.algs.BaseAlgo;
import core.file.algs.symmetric.enums.ModeOfOperationEnum;
import core.file.algs.symmetric.enums.PaddingModeEnum;
import core.utils.PasswordUtils;

// Done

public class DES extends BaseAlgo {

	private static final String ALGORITHM = "DES";
	private final Cipher CIPHER;
	private final Key KEY;
	private final byte[] IV;

	public DES(boolean encryptMode, ModeOfOperationEnum modeOfOperation, PaddingModeEnum paddingMode)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException {
		this((Key) null, null, encryptMode, modeOfOperation, paddingMode);
	}

	public DES(Key key, byte[] iv, boolean encryptMode, ModeOfOperationEnum modeOfOperation,
			PaddingModeEnum paddingMode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException {
		this.CIPHER = Cipher.getInstance(String.format("%s/%s/%s", DES.ALGORITHM, modeOfOperation, paddingMode));

		if (key == null) {
			key = new SecretKeySpec(PasswordUtils.getRandomByte(this.CIPHER.getBlockSize()), DES.ALGORITHM);
		}

		if (iv == null) {
			iv = PasswordUtils.getRandomByte(this.CIPHER.getBlockSize());
		}

		this.CIPHER.init(encryptMode ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

		this.KEY = key;
		this.IV = iv;
	}

	public DES(byte[] password, byte[] iv, boolean encryptMode, ModeOfOperationEnum modeOfOperation,
			PaddingModeEnum paddingMode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException {
		this.CIPHER = Cipher.getInstance(String.format("%s/%s/%s", DES.ALGORITHM, modeOfOperation, paddingMode));

		if (iv == null) {
			iv = PasswordUtils.getRandomByte(this.CIPHER.getBlockSize());
		}

		this.KEY = new SecretKeySpec(password, DES.ALGORITHM);
		this.IV = iv;

		this.CIPHER.init(encryptMode ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, this.KEY,
				new IvParameterSpec(this.IV));
	}

	public Key getKey() {
		return this.KEY;
	}

	public byte[] getIV() {
		return this.IV;
	}

	@Override
	public int outputBlockSize() {
		return this.CIPHER.getOutputSize(BaseAlgo.DEFAULT_BLOCK_SIZE);
	}

	@Override
	public byte[] doFinal(byte[] data) throws IllegalBlockSizeException, BadPaddingException {
		return this.CIPHER.doFinal(data);
	}

}
