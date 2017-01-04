package gui;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import core.file.algs.asymmetric.RSA;
import core.utils.FileIO;
import core.utils.Pair;
import exception.ExceptionInfo;

public final class Debug {

	public static void main(String[] args) throws ExceptionInfo {
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
		try {
			KeyPair keyPair = RSA.generateKeyPair(512);
			RSA rsa = new RSA(keyPair.getPublic(), 512, true);
			String s = "Khoa";
			byte[] en = rsa.doFinal(s.getBytes());
			System.out.println(new String(en));
			RSA rsa2 = new RSA(keyPair.getPrivate(), 512, false);
			byte[] de = rsa2.doFinal(en);
			System.out.println(new String(de));
		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
