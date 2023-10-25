//EG#1
//Java code to communicate with database and execute select query
//=======================================================
import com.mysql.cj.jdbc.Driver;
import java.sql.*;

class TestApp
{
      public static void main(String[] args)throws SQLException
      {
            //Step1. Load and register the Driver
            Driver driver = new Driver();//Creating driver object for MySQLDB
            DriverManager.registerDriver(driver);
            System.out.println("Driver registered succesfully");


             //Step2: Establish the connection b/w java and Database
             // JDBC URL SYNTAX:: <mainprotocol>:<subprotocol>:<subname>
             String url = "jdbc:mysql://localhost:3306/enterprisejavabatch";
           String username = "root";
           String password = "root123";

            Connection connection =
DriverManager.getConnection(url,username,password);
            System.out.println("Connection object is created:: " + connection);

           // Create a Statement Object
           Statement statement = connection.createStatement();
           System.out.println("Statement object is created:: " + statement);

           //Sending and execute the Query
           String sqlSelectQuery ="select sid,sname,sage,saddr from Student";
           ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
           System.out.println("ResultSet object is created:: " + resultSet);


           //Process the result from ResultSet
           System.out.println("SID\tSNAME\tSAGE\tSADDR");
           while(resultSet.next()){
                 Integer id = resultSet.getInt(1);
                 String name = resultSet.getString(2);
                 Integer age = resultSet.getInt(3);
                 String team = resultSet.getString(4);
                 System.out.println(id+"\t"+name+"\t"+age+"\t"+team);
           }

           //Close the Connection
           connection.close();
           System.out.println("Closing the connection...");

      }
}
//Output
//D:\JDBCPGMS>javac TestApp.java
//D:\JDBCPGMS>java TestApp
//Driver registered succesfully
//Connection object is created:: com.mysql.cj.jdbc.ConnectionImpl@4e41089d
//Statement object is created:: com.mysql.cj.jdbc.StatementImpl@23bb8443
//ResultSet object is created:: com.mysql.cj.jdbc.result.ResultSetImpl@7364985f
//SID     SNAME   SAGE     SADDR
//7       dhoni   41       CSK
//10      sachin 49        MI
//18      kohli   35       RCB
//45      rohith 37        MI
//Closing the connection...
