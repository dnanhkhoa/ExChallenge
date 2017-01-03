package core.utils;

public final class Pair<U, V> {

	private U left;
	private V right;

	public Pair(U left, V right) {
		this.left = left;
		this.right = right;
	}

	public U getLeft() {
		return this.left;
	}

	public V getRight() {
		return this.right;
	}
}
