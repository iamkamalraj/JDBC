package in.ineuron.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.ineuron.jdbcUtil.JdbcUtil;

public class BlobInsertOperation {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			//Getting the database connection using utility code
			connection = JdbcUtil.getJdbcConnection();

			String sqlInsertQuery = "insert into person(`name`,`image`)values(?,?)";

			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {

				// Setting the first index to String
				pstmt.setString(1, "nitin");

				// Image files is reaching to java application
				File f = new File("nitin.jpg");
				FileInputStream fis = new FileInputStream(f);

				// setting the input information from java and sending the data to database
				pstmt.setBlob(2, fis);

				System.out.println("Inserting image from :: " + f.getAbsolutePath());

				// Executing the Query to get the result
				int noOfRows = pstmt.executeUpdate();

				if (noOfRows == 1) {
					System.out.println("record inserted succesfully...");
				} else {
					System.out.println("No records inserted.....");
				}

			}

		} catch (SQLException e) {
			// handling logic of exception related to SQLException
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// hanlding logic of exception related to FileOperation
			e.printStackTrace();
		} catch (Exception e) {
			// hanlding logic of exception related to common problem
			e.printStackTrace();
		} finally {
			
			// closing the resource
			try {
				JdbcUtil.closeConnection(null, pstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
