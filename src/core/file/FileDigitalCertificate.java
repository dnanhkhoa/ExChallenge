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

	private static final String SIGNATURE = "-@CER@-";

	public static void sign(File inFile, Key key, int keySize, File cerFile)
			throws FileNotFoundException, NoSuchAlgorithmException, IOException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException, NoSuchPaddingException {

		String hash = FileIO.hashMD5File(inFile);
		RSA rsa = new RSA(key, keySize, true);
		try (FileOutputStream fileOutputStream = new FileOutputStream(cerFile)) {
			fileOutputStream.write(FileDigitalCertificate.SIGNATURE.getBytes());
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
			byte[] headerData = new byte[FileDigitalCertificate.SIGNATURE.length()];
			if (fileInputStream.read(headerData) != headerData.length || !SIGNATURE.equals(new String(headerData))) {
				return false;
			}

			byte[] buffer = new byte[(int) cerFile.length() - FileDigitalCertificate.SIGNATURE.length()];
			if (fileInputStream.read(buffer) == buffer.length) {
				byte[] decrypted = rsa.doFinal(buffer);
				hashTemp = new String(decrypted);
			}
		}
		return hashTemp != null && hash.contentEquals(hashTemp);
	}

	public static boolean isCerFile(File cerFile) {
		try (FileInputStream fileInputStream = new FileInputStream(cerFile)) {
			byte[] headerData = new byte[FileDigitalCertificate.SIGNATURE.length()];
			if (fileInputStream.read(headerData) == headerData.length && SIGNATURE.equals(new String(headerData))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
