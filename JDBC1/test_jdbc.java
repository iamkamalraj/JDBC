//Standard Steps followed for developing JDBC(JDBC4.X) Application
//=======================================================
//1. Load and register the Driver
//2. Establish the Connection b/w java application and database
//3. Create a Statement Object
//4. Send and execute the Query
//5. Process the result from ResultSet
//6. Close the Connection
//
//Step1:
//1. Load and register the Driver
//      A third party db vendor class which implements java.sql.Driver(I) is called
//as "Driver".
//      This class Object we need to create and register it with JRE to set up JDBC
//environment to run jdbc applications.
//
//Note:
//public class com.mysql.cj.jdbc.Driver extends
//com.mysql.cj.jdbc.NonRegisteringDriver implements java.sql.Driver {
//  public com.mysql.cj.jdbc.Driver() throws java.sql.SQLException;
//  static {};
//}
//
//In MySQL Jar, Driver class is implementing java.sql.Driver, so Driver class Object
//should be created and it should be registered
//to set up the JDBC environment inside JRE.
//
//
//2. Establish the   Connection b/w java application and database
//   public static   Connection getConnection(String url, String username,String
//password) throws   SQLException;
//   public static   Connection getConnection(String url, Properties) throws
//SQLException;
//   public static   Connection getConnection(String url) throws SQLException;
//
//The below creates the Object of Connection interface.
//      Connection connection = DriverManager.getConnection(url,username,password);
//                             |
//                        getConnection(url,username,password) created an object of
//class which implements Connection(I)
//                        that class object is collected by Connection(I).
//                        This feature in java refers to
//                                    a. Abstraction(hiding internal services)
//                                    b. polymorphism(making code run in 1:M forms)
//
//Can we create an Object for Interface?
// Answer. no
//Can we create an Object for a class which implements interface?
//Answer : yes
//
//3. Create a Statement Object
//    public abstract Statement createStatement() throws SQLException;
//    public abstract Statement createStatement(int,int) throws SQLException;
//    public abstract Statement createStatement(int,int,int) throws SQLException;
//
//     Statement statement = connection.createStatement();
//
//4. Send and execute the Query
//Query
//=====
//        From DB administrator perspective queries are classified into 5 types
//  1.   DDL (Create table,alter table,drop table,..)
//  2.   DML(Insert,update,delete)
//  3.   DQL(select)
//  4.   DCL(alter password,grant access)
//  5.   TCL(commit,rollback,savepoint)
//
//    According to java developer perspective, we catergorise queires into 2 types
//            a. Select Query
//            b. NonSelect Query
//
//Methods for executing the Query are
//      a. executeQuery() => for select query we use this method.
//      b. executeUpdate() => for insert,update and delete query we use this method.
//      c. execute() => for both select and non-select query we use this method
//
//public abstract ResultSet executeQuery(String sqlSelectQuery) throws SQLException;
//      String sqlSelectQuery ="select sid,sname,sage,saddr from Student";
//      ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
//
//5. Process the result from ResultSet
//            public abstract boolean next() throws java.sql.SQLException;
//                                                  |=> To check whether next Record
//is available or not
//                                                         returns true if available
//otherwise returns false.
//
//             System.out.println("SID\tSNAME\tSAGE\tSADDR");
//             while(resultSet.next()){
//                   Integer id = resultSet.getInt(1);
//                   String name = resultSet.getString(2);
//                   Integer age = resultSet.getInt(3);
//                   String team = resultSet.getString(4);
//                   System.out.println(id+"\t"+name+"\t"+age+"\t"+team);
//             }
//
//6. Close the Connection