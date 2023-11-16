package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JdbcUtil;

public class TransactionApp {

	public static void main(String[] args) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		Scanner scanner =null;

		try {

			// Getting the database connection using utility code
			connection = JdbcUtil.getJdbcConnection();

			stmt = connection.createStatement();
			System.out.println("Data before transaction");
			System.out.println("--------------------------");
			resultSet = stmt.executeQuery("select name,balance from accounts");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + "\t" + resultSet.getInt(2));
			}
			System.out.println();
			
			
			System.out.println("Transaction begins");

			//disabled the autocommit nature
			connection.setAutoCommit(false);

			
			stmt.executeUpdate("update accounts set balance = balance-3000 where name='sachin'");
			stmt.executeUpdate("update accounts set balance = balance+3000 where name='dhoni'");
			
			scanner = new Scanner(System.in);
			System.out.println("Can u please confirm the transaction of 30000... [YES/NO]");
			String option = scanner.next();
			if (option.equalsIgnoreCase("yes")) {
				connection.commit();
				System.out.println("Transaction commited");
			} else {
				connection.rollback();
				System.out.println("Transaction rollback");
			}
			
			
			System.out.println("DATA AFTER TRANSACTION");
			System.out.println("----------------------------");
			resultSet1 = stmt.executeQuery("select name,balance from accounts");
			while (resultSet1.next()) {
				System.out.println(resultSet1.getString(1) + "\t" + resultSet1.getInt(2));
			}
			

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
			if (scanner!=null) {
				scanner.close();
			}
		}

	}

}
