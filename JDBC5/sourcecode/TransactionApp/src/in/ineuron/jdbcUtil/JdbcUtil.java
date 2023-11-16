package in.ineuron.jdbcUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

	private JdbcUtil() {

	}

	public static Connection getJdbcConnection() throws SQLException, IOException {
		// resources used in jdbc application
		Connection connection = null;

		Properties props = new Properties();

		FileInputStream fis = new FileInputStream("src\\in\\ineuron\\properties\\jdbc.properties");
		props.load(fis);

		// 2. Establish the Connection
		String url = props.getProperty("url");
		String user = props.getProperty("user");
		String password = props.getProperty("password");

		connection = DriverManager.getConnection(url, user, password);

		if (connection != null)
			return connection;

		return connection;
	}

	public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection)
			throws SQLException {

		// 6. closing the resources used
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();

		}
		if (connection != null) {
			connection.close();
		}

	}

}
