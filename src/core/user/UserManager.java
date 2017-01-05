package core.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Done

public final class UserManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<User> users;

	public UserManager() {
		this.users = new ArrayList<>();
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void add(User user) {
		users.add(user);
	}

	public void remove(User user) {
		users.remove(user);
	}

	public User findUserByEmail(String email) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().equals(email)) {
				return users.get(i);
			}
		}
		return null;
	}

	public User login(String email, String password) {
		User user = this.findUserByEmail(email);
		if (user == null || !user.confirmPassword(password))
			return null;
		return user;
	}

	public static UserManager load(File inFile) {
		UserManager userManager = null;
		try (FileInputStream fileInputStream = new FileInputStream(inFile)) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
				userManager = (UserManager) objectInputStream.readObject();
			}
		} catch (Exception ex) {
			userManager = null;
		}
		return userManager;
	}

	public void save(File outFile) throws IOException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(outFile)) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
				objectOutputStream.writeObject(this);
			}
		}
	}
}
