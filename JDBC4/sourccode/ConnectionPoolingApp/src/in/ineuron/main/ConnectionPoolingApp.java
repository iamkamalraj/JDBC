package in.ineuron.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class ConnectionPoolingApp {

	public static void main(String[] args) throws SQLException {

		// Creating a pooled connection object
		MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();

		dataSource.setURL("jdbc:mysql://localhost:3306/enterprisejavabatch");
		dataSource.setUser("root");
		dataSource.setPassword("root123");

		// logical connection bought from connection pool
		Connection connection = dataSource.getConnection();

		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("select sid,sname,saddr from student");

		System.out.println("SID\tSNAME\tSADDRESS");
		System.out.println("===========================");
		while (resultSet.next()) {
			int sid = resultSet.getInt(1);
			String sname = resultSet.getString(2);
			String saddress = resultSet.getString(3);
			System.out.println(sid + "\t" + sname + "\t" + saddress);
		}

		// sending the connection object back to connection pool
		connection.close();

	}

}
