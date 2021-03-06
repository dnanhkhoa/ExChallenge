package core.model;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;

import exception.ExceptionInfo;

// Done

public final class FileModel {

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	private String name;
	private String path;
	private String size;
	private String type;
	private String modified;
	private ImageIcon icon;
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
				this.modified = "";
				this.icon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(back);
			}
		} else {
			this.name = file.getName();
			this.path = file.getPath();
			this.size = file.isFile() ? FileUtils.byteCountToDisplaySize(FileUtils.sizeOf(file)) : "";
			this.type = FileSystemView.getFileSystemView().getSystemTypeDescription(file);
			this.modified = SIMPLE_DATE_FORMAT.format(file.lastModified());
			this.icon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(file);
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

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public boolean isBack() {
		return isBack;
	}
}
