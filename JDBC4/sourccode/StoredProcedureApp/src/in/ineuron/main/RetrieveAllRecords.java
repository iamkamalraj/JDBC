package in.ineuron.main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.jdbcUtil.JdbcUtil;

/*DELIMITER $$

USE `enterprisejavabatch`$$

DROP PROCEDURE IF EXISTS `getAllStudents`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllStudents`()
BEGIN
		SELECT sid,sname,saddr
		FROM student;
	END$$

DELIMITER ;*/

public class RetrieveAllRecords {

	public static void main(String[] args) {

		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet resultSet = null;

		try {
			// Getting the database connection using utility code
			connection = JdbcUtil.getJdbcConnection();

			// Syntax for stroed procedure => {call procedure_name(?,?,?,....)}
			String storedProcedure = "{call getAllStudents()}";

			if (connection != null)
				cstmt = connection.prepareCall(storedProcedure);

			if (cstmt != null) {

				// execute the stored procedure
				cstmt.execute();

				// fetching the resultSet from StoredProcedure
				resultSet = cstmt.getResultSet();
			}
			if (resultSet != null) {

				System.out.println("SID\tSNAME\tSADDR");
				System.out.println("--------------------");
				while (resultSet.next()) {
					int sid = resultSet.getInt(1);
					String sname = resultSet.getString(2);
					String saddr = resultSet.getString(3);
					System.out.println(sid + "\t" + sname + "\t" + saddr);
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
				JdbcUtil.closeConnection(resultSet, cstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
