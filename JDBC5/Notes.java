//
//public class Test {
//
//}

//Transaction Management in JDBC
//===============================
//=> Process of combining all related operations into a single unit and executing on
//the rule
//     "either all or none", is called transaction management.
//=> Hence transaction is a single unit of work and it will work on the rule "either
//all or none".
//
//Case-1: Funds Transfer
//1. debit funds from sender's account
//2. credit funds into receiver's account
//All operations should be performed as a single unit only. If debit from sender's
//account completed
//and credit into receiver's account fails then there may be a chance of data
//inconsistency problems.
//
//Case-2: Movie Ticket Reservation
//1. Verify the status
//2. Reserve the tickets
//3. Payment
//4. issue tickets.
//All operations should be performed as a single unit only. If some operations
//success and some operations fails then
//there may be data inconsistency problems.
//
//Transaction Properties:
//Every Transaction should follow the following four ACID properties.
//1. A ➔ Atomiticity
//   Either all operations should be done or None.
//2. C ➔ Consistency(Reliabile Data)
//   It ensures bringing database from one consistent state to another consistent
//state.
//3. I ➔ isolation (Seperatation)
//   Ensures that transaction is isolated from other transactions
//4. D ➔ Durability
//   It means once transaction committed, then the results are permanent even in the
//case of system restarts, errors etc.
//
//
//Types of Transactions
//=====================
//There are two types of Transactions
//1. Local Transactions
//2. Global Transactions
//
//1. Local Transactions:
//     All operations in a transaction are executed over same database.
//     Eg: Funds transfer from one accoun to another account where both accounts in
//the same bank.
//2. Global Transactions:
//    All operations is a transaction are expected over different databases.
//    Eg: Funds Transfer from one account to another account and accounts are related
//to
//        different banks.
//
//Note:
//JDBC can provide support only for local transactions.
//If we want global transactions then we have to go for EJB(Enterprise Java Bean) or
//Spring framework.
//Process of Transaction Management in JDBC:
//1. Disable auto commit mode of JDBC
//   By default auto commit mode is enabled. i.e after executing every sql query, the
//changes will   be committed automatically
//   in the database.
//   We can disable auto commit mode as follows
//            con.setAutoCommit(false);
//
//2. If all operations completed then we can commit the transaction by using the
//following method.
//            con.commit();
//
//3. If any sql query fails then we have to rollback operations which are already
//completed by using rollback() method.
//            con.rollback();
//
//Program to demonstrate Transaction app
