package core.file;

import java.io.File;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import core.file.algs.BaseAlgo;
import core.file.algs.symmetric.AES;
import core.file.algs.symmetric.DES;
import core.file.algs.symmetric.enums.AlgoEnum;
import core.file.algs.symmetric.enums.ModeOfOperationEnum;
import core.file.algs.symmetric.enums.PaddingModeEnum;

public final class FileHandler {

	private static final String SIGNATURE = "ENCRYPTED";

	private BaseAlgo cipher;
	private List<String> paths;
	private boolean isEncrypted;

	public FileHandler() {
	}

	private void initCipher(boolean encryptMode, AlgoEnum algoEnum, ModeOfOperationEnum modeOfOperation,
			PaddingModeEnum paddingMode) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException {

		if (algoEnum == AlgoEnum.AES) {
			this.cipher = new AES(encryptMode, modeOfOperation, paddingMode);
		} else {
			this.cipher = new DES(encryptMode, modeOfOperation, paddingMode);
		}
	}

	public static boolean isEncryptedFile(File filePath) {
		return false;
	}

	public void encrypt() {
		// Pack
		// Zip
		// Encrypt
	}

	public void decrypt() {

	}
}
