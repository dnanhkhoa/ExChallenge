package core.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseHandler {

	private Connection connection = null;

	public DatabaseHandler(String dbFileName) {
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbFileName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (this.connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.connection = null;
			}
		}
	}
}
