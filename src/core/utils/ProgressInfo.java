package core.utils;

// Done

public final class ProgressInfo {

	private String name;
	private long total;
	private long current;

	public ProgressInfo() {
		this(0, 0);
	}

	public ProgressInfo(String name) {
		this(name, 0, 0);
	}

	public ProgressInfo(long total, long current) {
		this("Idle", total, current);
	}

	public ProgressInfo(String name, long total, long current) {
		this.name = name;
		this.total = total;
		this.current = current;
	}

	public void reset() {
		name = "Idle";
		total = 0;
		current = 0;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void increase() {
		this.current++;
	}

	public int getProgressValue() {
		return (int) (total > 0 ? current * 100 / total : 0);
	}
}
