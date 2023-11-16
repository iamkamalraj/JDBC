package in.ineuron.main;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class RowSetApp {

	public static void main(String[] args) throws SQLException {

		
		RowSetFactory factory = RowSetProvider.newFactory();

		JdbcRowSet jdbcRowSet = factory.createJdbcRowSet();
		jdbcRowSet.setUrl("jdbc:mysql:///enterprisejavabatch");
		jdbcRowSet.setUsername("root");
		jdbcRowSet.setPassword("root123");

		jdbcRowSet.setCommand("select * from student");
		jdbcRowSet.execute();

		System.out.println("Student details in forward direction");
		System.out.println("SID\tSNAME\tSAGE\tSADDR");
		System.out.println("------------------------------");
		while (jdbcRowSet.next()) {
			System.out.println(jdbcRowSet.getInt(1) + "\t" + jdbcRowSet.getString(2) + "\t" + jdbcRowSet.getInt(3)
					+ "\t" + jdbcRowSet.getString(4));
		}

		System.out.println();

		System.out.println("Student details in backward direction");
		System.out.println("SID\tSNAME\tSAGE\tSADDR");
		System.out.println("------------------------------");
		while (jdbcRowSet.previous()) {
			System.out.println(jdbcRowSet.getInt(1) + "\t" + jdbcRowSet.getString(2) + "\t" + jdbcRowSet.getInt(3)
					+ "\t" + jdbcRowSet.getString(4));
		}

		/*
		 * // performing insert operation using jdbcRowSet jdbcRowSet.moveToInsertRow();
		 * 
		 * jdbcRowSet.updateInt(1, 63); jdbcRowSet.updateString(2, "SKY");
		 * jdbcRowSet.updateInt(3, 31); jdbcRowSet.updateString(4, "MI");
		 * 
		 * jdbcRowSet.insertRow();
		 * 
		 * System.out.println("Record inserted succesfully");
		 */

		//take the cursor to the 4th row from the begining of the rowSet object
		jdbcRowSet.absolute(4);
		System.out.println(jdbcRowSet.getString(2));
		jdbcRowSet.deleteRow();
		System.out.println("Row deleted succesfully...");

	}

}
