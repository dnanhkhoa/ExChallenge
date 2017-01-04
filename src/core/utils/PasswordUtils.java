package core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class PasswordUtils {

	public static byte[] getRandomByte(int length) throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] buffer = new byte[length];
		sr.nextBytes(buffer);
		return buffer;
	}

	public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(salt);
		return StringUtils.byteToHex(md.digest(password.getBytes()));
	}
}
