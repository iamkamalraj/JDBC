package in.ineuron.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;

import in.ineuron.jdbcUtil.JdbcUtil;

public class ClobRetrievalOperation {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		FileWriter writer = null;

		try {
			// Getting the database connection using utility code
			connection = JdbcUtil.getJdbcConnection();

			String sqlSelectQuery = "select name,history from cities where name = ?";
			String name = "bengaluru";

			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);

			if (pstmt != null) {
				// Setting the first index to String
				pstmt.setString(1, name);

				// execute the Query
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {

				if (resultSet.next()) {
					// fetching the name
					String username = resultSet.getString(1);

					// fetching the image and keeping it in harddisk(just like downloading)
					Reader reader = resultSet.getCharacterStream(2);

					String fileName = "bengaluru_info.txt";
					File file = new File(fileName);

					writer = new FileWriter(file);

					// copying from inputstream to outputstream
					IOUtils.copy(reader, writer);
					writer.flush();

					System.out.println(username);
					System.out.println("File is saved to the location:: " + file.getAbsolutePath());

				} else {
					System.out.println("record not available for the given city:: " + name);
				}
			}

		} catch (SQLException e) {
			// handling logic of exception related to SQLException
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

			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
