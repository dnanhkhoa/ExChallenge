package core.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class PasswordUtils {

	public static byte[] getRandomByte(int length) throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] buffer = new byte[length];
		sr.nextBytes(buffer);
		return buffer;
	}

	public static String getRandomString(int length) {
		return null;
	}
}
