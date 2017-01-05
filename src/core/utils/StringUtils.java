package core.utils;

import java.math.BigInteger;

public final class StringUtils {

	public static String byteToHex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < bytes.length; ++i) {
			builder.append(Integer.toHexString((bytes[i] & 0xff) + 0x100).substring(1));
		}
		return builder.toString();
	}

	public static byte[] hexToBytes(String hex) {
		return new BigInteger(hex, 16).toByteArray();
	}
}
