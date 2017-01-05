package core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Observer;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FileUtils;

import core.file.algs.BaseAlgo;
import core.file.algs.asymmetric.RSA;
import core.file.algs.symmetric.AES;
import core.file.algs.symmetric.DES;
import core.file.algs.symmetric.enums.AlgoEnum;
import core.file.algs.symmetric.enums.ModeOfOperationEnum;
import core.file.algs.symmetric.enums.PaddingModeEnum;
import core.user.User;
import core.utils.FileIO;
import core.utils.ObservableModel;
import core.utils.ProgressInfo;
import exception.ExceptionInfo;

// Done

public final class FileHandler {

	private static final String SIGNATURE = "ENCRYPTED";

	private final ObservableModel observable;
	private final List<File> paths;
	private AlgoEnum algo;
	private ModeOfOperationEnum operation;
	private PaddingModeEnum padding;
	private boolean isCompress;
	private boolean isEncrypt;

	public FileHandler(List<File> paths) {
		this.isEncrypt = false;
		this.observable = new ObservableModel();
		this.paths = paths;
		this.algo = null;
		this.operation = null;
		this.padding = null;
		this.isCompress = false;
	}

	public FileHandler(List<File> paths, AlgoEnum algo, ModeOfOperationEnum operation, PaddingModeEnum padding,
			boolean isCompress) {

		this.observable = new ObservableModel();
		this.isEncrypt = true;
		this.paths = paths;
		this.algo = algo;
		this.operation = operation;
		this.padding = padding;
		this.isCompress = isCompress;
	}

	public void registerObserver(Observer observer) {
		this.observable.addObserver(observer);
	}

	public static boolean isEncryptedFile(File filePath) {
		try (FileUnwrapper fileUnwrapper = new FileUnwrapper(filePath, FileHandler.SIGNATURE)) {
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private BaseAlgo getCipher(Key key, byte[] iv) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException {

		BaseAlgo cipher = new BaseAlgo();
		if (this.algo == AlgoEnum.AES) {
			cipher = new AES(key, iv, this.isEncrypt, this.operation, this.padding);
		} else if (this.algo == AlgoEnum.DES) {
			cipher = new DES(key, iv, this.isEncrypt, this.operation, this.padding);
		}
		return cipher;
	}

	public void encrypt(File outFile, User user) throws Exception {

		if (this.paths.isEmpty()) {
			throw new ExceptionInfo("List is empty!");
		}

		File packedFile = File.createTempFile("enc", ".pack");
		File zippedFile = null;

		FileIO.pack(this.paths, packedFile, this.observable);
		File inFile = packedFile;

		if (this.isCompress) {
			zippedFile = File.createTempFile("enc", ".zip");
			FileIO.compress(packedFile, zippedFile, this.observable);
			inFile = zippedFile;
		}

		// Encrypt
		BaseAlgo cipher = this.getCipher(null, null);
		int blockSize = cipher.blockSize();

		RSA rsa = new RSA(user.getPublicKey(), user.getKeySize(), true);

		FileMeta fileMeta = new FileMeta();
		fileMeta.put("algo", this.algo);
		fileMeta.put("moo", this.operation);
		fileMeta.put("pm", this.padding);
		fileMeta.put("compress", this.isCompress);
		fileMeta.put("key", rsa.doFinal(cipher.getKey().getEncoded()));
		fileMeta.put("iv", cipher.getIV());
		fileMeta.put("block", cipher.outputBlockSize());

		if (this.observable == null) {

			try (FileWrapper fileWrapper = new FileWrapper(outFile, fileMeta, this.SIGNATURE)) {
				try (InputStream inputStream = new FileInputStream(inFile)) {

					byte[] buffer = new byte[blockSize];
					while (inputStream.read(buffer) > 0) {
						fileWrapper.write(cipher.doFinal(buffer));
					}

				}
			}
		} else {

			ProgressInfo progressInfo = new ProgressInfo("Encrypting...",
					(FileUtils.sizeOf(inFile) - 1) / blockSize + 1, 0);

			this.observable.setChanged();
			this.observable.notifyObservers(progressInfo);

			try (FileWrapper fileWrapper = new FileWrapper(outFile, fileMeta, this.SIGNATURE)) {
				try (InputStream inputStream = new FileInputStream(inFile)) {

					byte[] buffer = new byte[blockSize];
					while (inputStream.read(buffer) > 0) {
						fileWrapper.write(cipher.doFinal(buffer));

						progressInfo.increase();
						this.observable.setChanged();
						this.observable.notifyObservers(progressInfo);
					}

				}
			}
		}

		if (zippedFile != null) {
			FileUtils.forceDelete(zippedFile);
		}
		FileUtils.forceDelete(packedFile);
	}

	public void decrypt(File outDir, User user, String password) throws ClassNotFoundException, Exception {
		if (this.paths.isEmpty()) {
			throw new ExceptionInfo("List is empty!");
		}
		if (this.paths.size() > 1) {
			throw new ExceptionInfo("Can not process multi-file!");
		}
		if (!outDir.isDirectory()) {
			throw new ExceptionInfo("Path must be a directory!");
		}
		File inFile = this.paths.get(0);

		if (!FileHandler.isEncryptedFile(inFile)) {
			throw new ExceptionInfo("File is invalid!");
		}

		File tempFile = File.createTempFile("dec", "tmp");

		try (FileUnwrapper fileUnwrapper = new FileUnwrapper(inFile, this.SIGNATURE)) {
			FileMeta fileMeta = fileUnwrapper.readFileMeta();

			RSA rsa = new RSA(user.getPrivateKey(password), user.getKeySize(), false);

			this.isEncrypt = false;
			this.algo = (AlgoEnum) fileMeta.get("algo");
			this.operation = (ModeOfOperationEnum) fileMeta.get("moo");
			this.padding = (PaddingModeEnum) fileMeta.get("pm");
			this.isCompress = (boolean) fileMeta.get("compress");
			byte[] keyData = rsa.doFinal((byte[]) fileMeta.get("key"));

			Key key = new SecretKeySpec(keyData, 0, keyData.length, this.algo.toString());
			byte[] iv = (byte[]) fileMeta.get("iv");

			BaseAlgo cipher = this.getCipher(key, iv);

			int blockSize = (int) fileMeta.get("block");

			if (this.observable == null) {

				try (OutputStream outputStream = new FileOutputStream(tempFile)) {

					byte[] buffer = new byte[blockSize];
					int bytesRead = 0;
					while ((bytesRead = fileUnwrapper.read(buffer)) > 0) {
						outputStream.write(buffer, 0, bytesRead);
					}

				}

			} else {

				ProgressInfo progressInfo = new ProgressInfo("Decrypting...",
						(FileUtils.sizeOf(inFile) - 1) / blockSize + 1, 0);

				this.observable.setChanged();
				this.observable.notifyObservers(progressInfo);

				try (OutputStream outputStream = new FileOutputStream(tempFile)) {

					byte[] buffer = new byte[blockSize];
					while (fileUnwrapper.read(buffer) > 0) {
						outputStream.write(cipher.doFinal(buffer));

						progressInfo.increase();
						this.observable.setChanged();
						this.observable.notifyObservers(progressInfo);
					}

				}

			}

			inFile = tempFile;
			File unzipFile = null;

			if (this.isCompress) {
				unzipFile = File.createTempFile("dec", "tmp");
				FileIO.decompress(inFile, unzipFile, this.observable);
				inFile = unzipFile;
			}

			FileIO.unpack(inFile, outDir, this.observable);

			if (unzipFile != null) {
				FileUtils.forceDelete(unzipFile);
			}
			FileUtils.forceDelete(tempFile);
		}
	}
}
