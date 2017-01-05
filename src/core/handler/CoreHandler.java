package core.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import core.user.User;
import core.user.UserManager;

public final class CoreHandler {

	private static CoreHandler _instance;

	public UserManager userManager;
	public User currentUser;
	public List<File> fileBuffer;
	public boolean isMove;

	private CoreHandler() {
		UserManager userManager = UserManager.load(new File("users.db"));
		if (userManager == null) {
			userManager = new UserManager();
		}
		this.userManager = userManager;
		this.currentUser = null;
		this.fileBuffer = new ArrayList<>();
		this.isMove = false;
	}

	public static synchronized CoreHandler getInstance() {
		if (_instance == null) {
			_instance = new CoreHandler();
		}
		return _instance;
	}

	public void close() {
		try {
			this.userManager.save(new File("users.db"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
