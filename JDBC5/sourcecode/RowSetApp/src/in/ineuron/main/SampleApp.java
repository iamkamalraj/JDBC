package in.ineuron.main;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class SampleApp {

	public static void main(String[] args) throws SQLException {

		//factory method which is used to create an object of any rowset object
		RowSetFactory factory = RowSetProvider.newFactory();
		
		
		JdbcRowSet jdbcRowSet = factory.createJdbcRowSet();
		CachedRowSet cachedRowSet = factory.createCachedRowSet();
		FilteredRowSet filteredRowSet = factory.createFilteredRowSet();
		JoinRowSet joinRowSet = factory.createJoinRowSet();
		WebRowSet webRowSet = factory.createWebRowSet();

	}

}
