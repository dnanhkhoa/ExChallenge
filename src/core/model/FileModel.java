package core.model;

import java.io.File;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;

import exception.ExceptionInfo;

// Done

public final class FileModel {

	private String name;
	private String path;
	private String size;
	private String type;
	private Icon icon;
	private boolean isBack;

	public FileModel(String path) throws ExceptionInfo {
		this(path, false);
	}

	public FileModel(String path, boolean isBack) throws ExceptionInfo {
		File file = new File(path).getAbsoluteFile();
		if (!file.exists()) {
			throw new ExceptionInfo("Path does not exist!");
		}
		this.isBack = isBack;
		if (this.isBack) {
			if (file.getParentFile() == null) {
				throw new ExceptionInfo("Path must be a sub directory!");
			} else {
				File back = new File(path, "..");
				if (!back.exists()) {
					throw new ExceptionInfo("Path does not exist!");
				}

				this.name = back.getName();
				this.path = back.getParent();
				this.size = "";
				this.type = FileSystemView.getFileSystemView().getSystemTypeDescription(back);
				this.icon = FileSystemView.getFileSystemView().getSystemIcon(back);
			}
		} else {
			this.name = file.getName();
			this.path = file.getPath();
			this.size = FileUtils.byteCountToDisplaySize(FileUtils.sizeOf(file));
			this.type = FileSystemView.getFileSystemView().getSystemTypeDescription(file);
			this.icon = FileSystemView.getFileSystemView().getSystemIcon(file);
		}
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String getSize() {
		return size;
	}

	public String getType() {
		return type;
	}

	public Icon getIcon() {
		return icon;
	}

	public boolean isBack() {
		return isBack;
	}
}
