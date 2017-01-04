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
import core.file.FileSizePair;
import core.file.FileUnwrapper;
import core.file.FileWrapper;
import exception.ExceptionInfo;

public final class FileIO {

	private static final int BLOCK_SIZE = 512;

	// Done
	public static String hashMD5File(File file) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
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
	public static Pair<List<String>, List<FileSizePair>> scanDir(File dirPath) throws ExceptionInfo {
		if (!dirPath.isDirectory()) {
			throw new ExceptionInfo("Path must be a valid directory!");
		}

		URI base = dirPath.toURI();
		if (dirPath.getParentFile() != null) {
			base = dirPath.getParentFile().toURI();
		}

		List<String> dirs = new ArrayList<>();
		List<FileSizePair> files = new ArrayList<>();

		Collection<File> collection = FileUtils.listFilesAndDirs(dirPath, TrueFileFilter.INSTANCE,
				TrueFileFilter.INSTANCE);
		for (File file : collection) {
			if (file.isDirectory()) {
				dirs.add(base.relativize(file.toURI()).getPath());
			} else {
				files.add(new FileSizePair(file.getPath(), base.relativize(file.toURI()).getPath(),
						FileUtils.sizeOf(file)));
			}
		}
		return new Pair<List<String>, List<FileSizePair>>(dirs, files);
	}

	// Done
	public static Pair<List<String>, List<FileSizePair>> scanDir(List<File> paths) throws ExceptionInfo {
		List<FileSizePair> files = new ArrayList<>();
		List<String> dirs = new ArrayList<>();
		for (File path : paths) {
			if (path.isDirectory()) {
				Pair<List<String>, List<FileSizePair>> pair = scanDir(path);
				dirs.addAll(pair.getLeft());
				files.addAll(pair.getRight());
			} else {
				files.add(new FileSizePair(path.getPath(), path.getName(), FileUtils.sizeOf(path)));
			}
		}
		return new Pair<List<String>, List<FileSizePair>>(dirs, files);
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

	// Done
	public static void pack(List<File> inFiles, File outFile) throws IOException, Exception {
		FileMeta fileMeta = new FileMeta();

		Pair<List<String>, List<FileSizePair>> pair = FileIO.scanDir(inFiles);

		List<FileSizePair> fileSizePairs = new ArrayList<>();
		for (FileSizePair fileSizePair : pair.getRight()) {
			fileSizePairs.add(new FileSizePair(null, fileSizePair.getRelativePath(), fileSizePair.getSize()));
		}

		fileMeta.put("dirs", pair.getLeft());
		fileMeta.put("files", fileSizePairs);

		try (FileWrapper fileWrapper = new FileWrapper(outFile, fileMeta)) {
			for (FileSizePair fileSizePair : pair.getRight()) {
				try (InputStream inputStream = new FileInputStream(fileSizePair.getAbsolutePath())) {
					byte[] buffer = new byte[FileIO.BLOCK_SIZE];
					int bytesRead = 0;
					while ((bytesRead = inputStream.read(buffer)) > 0) {
						fileWrapper.write(buffer, 0, bytesRead);
					}
				}
			}
		}
	}

	// Done
	@SuppressWarnings("unchecked")
	public static void unpack(File inFile, File outDir) throws ClassNotFoundException, ExceptionInfo, Exception {
		if (!outDir.isDirectory()) {
			throw new ExceptionInfo("Output path must be a directory!");
		}
		try (FileUnwrapper fileUnwrapper = new FileUnwrapper(inFile)) {
			FileMeta fileMeta = fileUnwrapper.readFileMeta();
			List<String> dirs = (List<String>) fileMeta.get("dirs");
			List<FileSizePair> files = (List<FileSizePair>) fileMeta.get("files");
			for (String dir : dirs) {
				FileUtils.forceMkdir(new File(outDir, dir));
			}
			for (FileSizePair file : files) {
				try (OutputStream outputStream = new FileOutputStream(new File(outDir, file.getRelativePath()))) {
					byte[] buffer = new byte[FileIO.BLOCK_SIZE];
					int bytesRead = 0;
					long maxBytesRead = file.getSize();
					while ((bytesRead = fileUnwrapper.read(buffer, 0,
							Math.toIntExact(Math.min(BLOCK_SIZE, maxBytesRead)))) > 0) {
						outputStream.write(buffer, 0, bytesRead);
						maxBytesRead -= bytesRead;
					}
				}
			}
		}
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
