package core.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import core.file.algs.asymmetric.RSA;
import core.file.algs.symmetric.AES;
import core.file.algs.symmetric.enums.ModeOfOperationEnum;
import core.file.algs.symmetric.enums.PaddingModeEnum;
import core.utils.PasswordUtils;

// Done

public final class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String IV = "@UserExChallenge";

	private String email;
	private String password;
	private String name;
	private String birthday;
	private String phone;
	private String address;
	private Key publicKey;
	private byte[] privateKey;
	private int keySize;
	private byte[] salt;
	private int keySizeIndex;

	public User(String email, String password, String name, String birthday, String phone, String address, int keySize,
			int keySizeIndex) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		this.email = email;
		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.keySize = keySize;
		this.keySizeIndex = keySizeIndex;
		this.salt = PasswordUtils.getRandomByte(32);
		this.password = PasswordUtils.hashPassword(password, this.salt);

		AES aes = new AES(PasswordUtils.md5(this.password), User.IV.getBytes(), true, ModeOfOperationEnum.CBC,
				PaddingModeEnum.PKCS5Padding);

		KeyPair keyPair = RSA.generateKeyPair(this.keySize);
		this.publicKey = keyPair.getPublic();
		this.privateKey = aes.doFinal(keyPair.getPrivate().getEncoded());
	}

	public void update(String password, String name, String birthday, String phone, String address, int keySize,
			int keySizeIndex) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;

		if (!password.isEmpty()) {

			this.keySize = keySize;
			this.keySizeIndex = keySizeIndex;
			this.salt = PasswordUtils.getRandomByte(32);
			this.password = PasswordUtils.hashPassword(password, this.salt);

			AES aes = new AES(PasswordUtils.md5(this.password), User.IV.getBytes(), true, ModeOfOperationEnum.CBC,
					PaddingModeEnum.PKCS5Padding);

			KeyPair keyPair = RSA.generateKeyPair(this.keySize);
			this.publicKey = keyPair.getPublic();
			this.privateKey = aes.doFinal(keyPair.getPrivate().getEncoded());

		}
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public Key getPublicKey() {
		return publicKey;
	}

	public Key getPrivateKey(String password) {
		try {
			password = PasswordUtils.hashPassword(password, this.salt);

			AES aes = new AES(PasswordUtils.md5(password), User.IV.getBytes(), false, ModeOfOperationEnum.CBC,
					PaddingModeEnum.PKCS5Padding);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(aes.doFinal(this.privateKey)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getKeySize() {
		return keySize;
	}

	public byte[] getSalt() {
		return salt;
	}

	public int getKeySizeIndex() {
		return keySizeIndex;
	}

	public boolean confirmPassword(String password) {
		return this.password.equals(PasswordUtils.hashPassword(password, this.salt));
	}

	public void save(File outFile) throws FileNotFoundException, IOException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(outFile)) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
				objectOutputStream.writeObject(this);
			}
		}
	}

	public static User load(File inFile) {
		User user = null;
		try (FileInputStream fileInputStream = new FileInputStream(inFile)) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
				user = (User) objectInputStream.readObject();
			}
		} catch (Exception ex) {
			user = null;
		}
		return user;
	}

	@Override
	public boolean equals(Object obj) {
		return this.email.equals(((User) obj).getEmail());
	}

	@Override
	public String toString() {
		return String.format("User: %s [%s]", this.email, this.name.isEmpty() ? "None" : this.name);
	}
}