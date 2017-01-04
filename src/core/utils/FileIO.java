package core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import core.file.FileMeta;
import core.file.FileWrapper;
import exception.ExceptionInfo;

public final class FileIO {

	private static final int BLOCK_SIZE = 512;

	// Done
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

	// Done
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

	// Done
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

	// Done
	public static void compress(File inFile, File outFile) throws FileNotFoundException, IOException {
		try (InputStream inputStream = new FileInputStream(inFile)) {
			try (OutputStream outputStream = new FileOutputStream(outFile)) {
				try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {

					ZipEntry zipEntry = new ZipEntry(inFile.getName());

					zipOutputStream.putNextEntry(zipEntry);

					byte[] buffer = new byte[FileIO.BLOCK_SIZE];
					int bytesRead = 0;
					while ((bytesRead = inputStream.read(buffer)) > 0) {
						zipOutputStream.write(buffer, 0, bytesRead);
					}

					zipOutputStream.closeEntry();
				}
			}
		}
	}

	// Done
	public static void decompress(File inFile, File outFile) throws FileNotFoundException, IOException {
		try (InputStream inputStream = new FileInputStream(inFile)) {
			try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
				try (OutputStream outputStream = new FileOutputStream(outFile)) {
					zipInputStream.getNextEntry();

					byte[] buffer = new byte[FileIO.BLOCK_SIZE];
					int bytesRead = 0;
					while ((bytesRead = zipInputStream.read(buffer)) > 0) {
						outputStream.write(buffer, 0, bytesRead);
					}

					zipInputStream.closeEntry();
				}
			}
		}
	}

	public static void pack(String baseDir, List<File> inFiles, File outFile) {
		FileMeta fileMeta = new FileMeta();
		try {
			Pair<List<String>, List<String>> pair = FileIO.scanDir(inFiles);
			
			List<File> files = new ArrayList<>();
			for (String file : pair.getRight()) {
				
			}
			
			fileMeta.put("dirs", pair.getLeft());
			//fileMeta.put("files", p);
			
			try (FileWrapper fileWrapper = new FileWrapper(outFile, fileMeta)) {
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ExceptionInfo e) {
			e.printStackTrace();
		}
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
