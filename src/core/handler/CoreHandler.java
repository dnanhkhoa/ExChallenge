package core.handler;

public final class CoreHandler {

	private static CoreHandler _instance;

	private CoreHandler() {
	}

	public static synchronized CoreHandler getInstance() {
		if (_instance == null) {
			_instance = new CoreHandler();
		}
		return _instance;
	}
}
