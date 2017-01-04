package core.file;

import java.io.Serializable;
import java.util.HashMap;

public final class FileMeta implements Serializable {

	private static final long serialVersionUID = 1L;

	private HashMap<String, Object> header;

	public FileMeta() {
		this.header = new HashMap<>();
	}

	public FileMeta(HashMap<String, Object> header) {
		this.header = new HashMap<>(header);
	}

	public void add(String key, Object value) {
		this.header.put(key, value);
	}

	public void remove(String key) {
	}

	public boolean containsKey(String key) {
		return this.header.containsKey(key);
	}

	public int size() {
		return this.header.size();
	}
}
