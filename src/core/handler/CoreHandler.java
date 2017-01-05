package core.handler;

import core.user.User;
import core.user.UserManager;

public final class CoreHandler {

	private static CoreHandler _instance;

	public UserManager userManager;
	public User currentUser;

	private CoreHandler() {
		this.currentUser = null;
	}

	public static synchronized CoreHandler getInstance() {
		if (_instance == null) {
			_instance = new CoreHandler();
		}
		return _instance;
	}
}
