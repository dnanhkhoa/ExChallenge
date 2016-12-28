package gui;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

public final class Debug {

	public static void main(String[] args) {
		File[] fs = FileSystemView.getFileSystemView().getFiles(FileSystemView.getFileSystemView().getHomeDirectory(), true);
		for (File f : fs) {
			File[] fs1 = FileSystemView.getFileSystemView().getFiles(f, true);
			for (File f1 : fs1) {
				System.out.println(f1);
			}
		}
	}

}
