package core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class PasswordUtils {

	public static byte[] getRandomByte(int length) {
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] buffer = new byte[length];
			sr.nextBytes(buffer);
			return buffer;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String hashPassword(String password, byte[] salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			return StringUtils.byteToHex(md.digest(password.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
