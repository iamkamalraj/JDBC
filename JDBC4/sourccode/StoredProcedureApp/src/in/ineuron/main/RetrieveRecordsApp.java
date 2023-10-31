package in.ineuron.main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import in.ineuron.jdbcUtil.JdbcUtil;

/*DELIMITER $$

CREATE
    [DEFINER = { user | CURRENT_USER }]
    PROCEDURE `enterprisejavabatch`.`getStudentsById`(IN id INT,OUT stdName VARCHAR(20),OUT stdAddr VARCHAR(20))
    
	BEGIN
		SELECT sname,saddr INTO stdName,stdAddr 
		FROM student
		WHERE sid = id;
	END$$

DELIMITER ;*/

public class RetrieveRecordsApp {

	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement cstmt = null;
		Integer id = 7;

		try {
			// Getting the database connection using utility code
			connection = JdbcUtil.getJdbcConnection();

			// Syntax for stroed procedure => {call procedure_name(?,?,?,....)}
			String storedProcedure = "{call getStudentsById(?,?,?)}";

			if (connection != null)
				cstmt = connection.prepareCall(storedProcedure);

			if (cstmt != null) {
				// before calling set the input value to StoredProcedure
				cstmt.setInt(1, id);
				
				//register the Outputparameter with the specific data for conversion
				//use JDBCTypes to map all java datatypes to dbspecific datatypes
				cstmt.registerOutParameter(2, Types.VARCHAR);
				cstmt.registerOutParameter(3, Types.VARCHAR);
				
				//execute the query
				cstmt.execute();
				
				//Retreiving the value
				System.out.println("Name   of the student is :: "+cstmt.getString(2));
				System.out.println("Addres of the student is :: "+cstmt.getString(3));
				
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
				JdbcUtil.closeConnection(null, cstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
