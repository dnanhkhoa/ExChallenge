package core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import exception.ExceptionInfo;

public final class FileIO {

	private static final int BLOCK_SIZE = 512;

	public static String hashSHA256File(String file)
			throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] buffer = new byte[FileIO.BLOCK_SIZE];
		int bytesRead = 0;
		try (InputStream is = new FileInputStream(file)) {
			while ((bytesRead = is.read(buffer)) > 0) {
				md.update(buffer, 0, bytesRead);
			}
		}
		return StringUtils.byteToHex(md.digest());
	}

	public static Pair<List<String>, List<String>> scanDir(File dirPath) throws ExceptionInfo {
		if (!dirPath.isDirectory()) {
			throw new ExceptionInfo("Path must be a valid directory!");
		}

		URI base = dirPath.toURI();
		if (dirPath.getParentFile() != null) {
			base = dirPath.getParentFile().toURI();
		}

		List<String> files = new ArrayList<>();
		List<String> dirs = new ArrayList<>();

		Collection<File> collection = FileUtils.listFilesAndDirs(dirPath, TrueFileFilter.INSTANCE,
				TrueFileFilter.INSTANCE);
		for (File file : collection) {
			if (file.isDirectory()) {
				dirs.add(base.relativize(file.toURI()).getPath());
			} else {
				files.add(base.relativize(file.toURI()).getPath());
			}
		}
		return new Pair<List<String>, List<String>>(dirs, files);
	}

	public static Pair<List<String>, List<String>> scanDir(List<File> paths) throws ExceptionInfo {
		List<String> files = new ArrayList<>();
		List<String> dirs = new ArrayList<>();
		for (File path : paths) {
			if (path.isDirectory()) {
				Pair<List<String>, List<String>> pair = scanDir(path);
				dirs.addAll(pair.getLeft());
				files.addAll(pair.getRight());
			} else {
				files.add(path.getName());
			}
		}
		return new Pair<List<String>, List<String>>(dirs, files);
	}

	public static void compress(File inFile, File outFile) {

	}

	public static void decompress(File inFile, File outFile) {

	}

	public static void pack(String baseDir, List<String> inFiles, File outFile) {

	}

	public static void unpack(File inFile, File outDir) {

	}

	public static void copyFiles() {

	}

	public static void moveFiles() {

	}

	public static void deleteFiles() {

	}

	public static void renameFile() {

	}
}
