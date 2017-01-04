package core.user;

import java.security.Key;

import core.utils.PasswordUtils;

public final class User {

	String email;
	String password;
	String name;
	String birthday;
	String phone;
	String address;
	Key privateKey;
	Key publicKey;
	byte[] salt;
	
	public User(String _email, String _password, String _name, String _birthday, String _phone, String _address) {
		this.email = _email;
		this.name = _name;
		this.birthday = _birthday;
		this.phone = _phone;
		this.address = _address;
		this.salt = PasswordUtils.getRandomByte(32);
		this.password = PasswordUtils.hashPassword(_password, salt);
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Key getPrivateKey() {
		return privateKey;
	}


	public void setPrivateKey(Key privateKey) {
		this.privateKey = privateKey;
	}


	public Key getPublicKey() {
		return publicKey;
	}


	public void setPublicKey(Key publicKey) {
		this.publicKey = publicKey;
	}

	public byte[] getSalt() {
		return salt;
	}


	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.email.equals(((User)obj).email);

	}
}