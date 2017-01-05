package core.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class FileManagerModel {

	private File currentPath;
	private List<FileModel> fileModels;

	public FileManagerModel() {
		this.fileModels = new ArrayList<>();
	}

	public void update() {

	}

	public File getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(File currentPath) {
		this.currentPath = currentPath;
	}
}
