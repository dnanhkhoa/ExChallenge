package gui;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.io.FileUtils;

import core.file.FileDigitalCertificate;
import core.file.FileMeta;
import core.file.FileUnwrapper;
import core.file.FileWrapper;
import core.file.algs.asymmetric.RSA;
import core.utils.FileIO;
import core.utils.Pair;
import exception.ExceptionInfo;

public final class Debug {

	public static void main(String[] args) throws Exception {
		/*
		 * File[] fs =
		 * FileSystemView.getFileSystemView().getFiles(FileSystemView.
		 * getFileSystemView().getHomeDirectory(), true); for (File f : fs) {
		 * File[] fs1 = FileSystemView.getFileSystemView().getFiles(f, true);
		 * for (File f1 : fs1) { System.out.println(f1); } }
		 */
		
		/*
		List<File> f = new ArrayList<>();
		f.add(new File("C:\\Users\\Anh Khoa\\Desktop\\BT3\\RSAFileCryptor\\src1\\structure"));
		f.add(new File("C:\\Users\\Anh Khoa\\Desktop\\BT3\\RSAFileCryptor\\src1\\enums"));
		f.add(new File("C:\\Users\\Anh Khoa\\Desktop\\BT3\\RSAFileCryptor\\src1\\gui\\MainFrame.java"));
		Pair<List<String>,List<String>> pair = FileIO.scanDir(f);
		for (String s : pair.getRight()) {
			System.out.println(s);
		}
		*/
		/*
		try {
			KeyPair keyPair = RSA.generateKeyPair(512);
			RSA rsa = new RSA(keyPair.getPrivate(), 512, true);
			byte[] en = rsa.doFinal(FileIO.hashMD5File(new File("C:\\Users\\Anh Khoa\\Desktop\\Phuc\\mnist.pkl.gz")).getBytes());
			System.out.println(new String(en));
			RSA rsa2 = new RSA(keyPair.getPublic(), 512, false);
			byte[] de = rsa2.doFinal(en);
			System.out.println(new String(de));
		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		File file = new File("C:\\Users\\Anh Khoa\\Desktop\\1312288\\FileProtector\\src");
		List<File> files = new ArrayList<>();
		files.add(file);
		files.add(new File("C:\\Users\\Anh Khoa\\Desktop\\1312288\\FileProtector\\release"));
		File outFile = new File("C:\\Users\\Anh Khoa\\Desktop\\1312288\\FileProtector\\123.abc");
		FileIO.pack(files, outFile);
		*/
		
		/*
		File f = new File("C:\\Users\\Anh Khoa\\Desktop\\1312288\\FileProtector\\123.abc");
		FileIO.unpack(f, new File("C:\\Users\\Anh Khoa\\Desktop\\1312288\\FileProtector\\123456"));
		*/
		
		/*
		FileMeta fileMeta = new FileMeta();
		fileMeta.put("Hello", "Khoa Hello Khoa");
		FileWrapper fileWrapper = new FileWrapper(file, fileMeta);
		fileWrapper.write("Dương Nguyễn Anh Khoa".getBytes("UTF-8"));
		fileWrapper.close();
		*/
		//FileIO.decompress(file, new File("C:\\Users\\Anh Khoa\\Desktop\\1312288\\FileProtector\\123.mkv"));
		/*
		KeyPair keyPair = RSA.generateKeyPair(512);
		File inFile = new File("C:\\Users\\Anh Khoa\\Desktop\\Phuc\\mnist.pkl.gz");
		File cerFile = new File("C:\\Users\\Anh Khoa\\Desktop\\Phuc\\mnist.pkl.cer");
		FileDigitalCertificate.sign(inFile, keyPair.getPrivate(), 512, cerFile);
		
		System.out.println(FileDigitalCertificate.verify(inFile, keyPair.getPublic(), 512, cerFile));
		*/
	}

}
