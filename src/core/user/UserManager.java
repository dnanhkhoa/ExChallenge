package core.user;

import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public final class UserManager {

	private List<User> users;

	public UserManager() {
		this.users = new ArrayList<>();
	}

	public void add() {

	}

	public void remove(String email) {

	}

	public User findUserByEmail(String email) {
		return null;
	}

	public User findUserByPublicKey(Key publicKey) {
		return null;
	}

	public static UserManager load(String filePath) {
		return null;
	}

	public void store(String filePath) {

	}
}
