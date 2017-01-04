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

	public void add(User user) {
		users.add(user);
	}

	public void remove(String email) {
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getEmail().equals(email)){
				users.remove(i);
			}
		}
	}

	public User findUserByEmail(String email) {
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getEmail().equals(email)){
				return users.get(i);
			}
		}		
		return null;
	}

	public User findUserByPublicKey(Key publicKey) {
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getPublicKey().equals(publicKey)){
				return users.get(i);
			}
		}		
		return null;
	}

	public static UserManager load(String filePath) {
		return null;
	}

	public void store(String filePath) {

	}
	
	public User checkLogin(String email, String password){
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getEmail().equals(email)){
				if(users.get(i).getPassword().equals(password))
					return users.get(i);
			}
		}		
		return null;
	}
	
	
}
