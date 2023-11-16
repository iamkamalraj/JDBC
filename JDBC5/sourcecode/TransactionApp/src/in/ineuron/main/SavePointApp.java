package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import in.ineuron.jdbcUtil.JdbcUtil;

public class SavePointApp {

	public static void main(String[] args) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try {

			// Getting the database connection using utility code
			connection = JdbcUtil.getJdbcConnection();

			stmt = connection.createStatement();

			connection.setAutoCommit(false);

			stmt.executeUpdate("insert into politicians values('modi','bjp')");
			stmt.executeUpdate("insert into politicians values('stalin','dmk')");
			stmt.executeUpdate("insert into politicians values('babu','tdp')");

			Savepoint sp = connection.setSavepoint();
			stmt.executeUpdate("insert into politicians values('rahul','bjp')");
			System.out.println("Opps' something went wrong need to rollback....");
			connection.rollback(sp);

			connection.commit();

		} catch (SQLException e) {
			// handling logic of exception related to SQLException
			e.printStackTrace();
		} catch (IOException e) {
			// hanlding logic of exception related to FileOperation
			e.printStackTrace();
		} catch (Exception e) {
			// hanlding logic of exception related to common problem
			e.printStackTrace();
		} finally {
			// closing the resource
			try {
				JdbcUtil.closeConnection(resultSet, stmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
