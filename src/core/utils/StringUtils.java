package core.utils;

public final class StringUtils {

	public static String byteToHex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < bytes.length; ++i) {
			builder.append(Integer.toHexString((bytes[i] & 0xff) + 0x100).substring(1));
		}
		return builder.toString();
	}

}
