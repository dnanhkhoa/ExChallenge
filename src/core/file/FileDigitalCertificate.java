package core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import core.file.algs.asymmetric.RSA;
import core.utils.FileIO;

// Done

public final class FileDigitalCertificate {

	public static void sign(File inFile, Key key, int keySize, File cerFile)
			throws FileNotFoundException, NoSuchAlgorithmException, IOException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException, NoSuchPaddingException {

		String hash = FileIO.hashMD5File(inFile);
		RSA rsa = new RSA(key, keySize, true);
		try (FileOutputStream fileOutputStream = new FileOutputStream(cerFile)) {
			fileOutputStream.write(rsa.doFinal(hash.getBytes()));
		}
	}

	public static boolean verify(File inFile, Key key, int keySize, File cerFile)
			throws FileNotFoundException, NoSuchAlgorithmException, IOException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException, NoSuchPaddingException {

		String hash = FileIO.hashMD5File(inFile);

		RSA rsa = new RSA(key, keySize, false);

		String hashTemp = null;
		try (FileInputStream fileInputStream = new FileInputStream(cerFile)) {
			byte[] buffer = new byte[(int) cerFile.length()];
			if (fileInputStream.read(buffer) == buffer.length) {
				byte[] decrypted = rsa.doFinal(buffer);
				hashTemp = new String(decrypted);
			}
		}
		return hash.contentEquals(hashTemp);
	}
}
