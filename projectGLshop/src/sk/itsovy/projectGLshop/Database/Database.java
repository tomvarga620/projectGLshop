package sk.itsovy.projectGLshop.Database;

import sk.itsovy.projectGLshop.bill.Bill;
import sk.itsovy.projectGLshop.main.Globals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private static Database db = new Database();

    private Database(){

    }

    public static Database getInstance(){
        return db;
    }

    public void insertNewBill(Bill bill) throws SQLException {
        Connection conn = Globals.getConnection();
        PreparedStatement sqlPreparedStatement = null;
        PreparedStatement secondSqlStatement = null;
        try {
            //set connection auto commit to false
            conn.setAutoCommit(false);

            sqlPreparedStatement = conn.prepareStatement("INSERT INTO Bill (date,totalPrice,time) VALUES (?,?,?)");
            //set all the values in the prepared statement
            sqlPreparedStatement.setString(1, "string");
            sqlPreparedStatement.setString(2, "anotherstring");
            sqlPreparedStatement.setString(2, "anotherstring");

            sqlPreparedStatement.executeUpdate();

            secondSqlStatement = conn.prepareStatement("INSERT INTO Bill (OrderID,Name,Price,Count,Unit) VALUES (?,?,?,?,?)");
            //set all the values in the prepared statement
            secondSqlStatement.setString(1, "whatever");
            secondSqlStatement.setString(2, "sample");
            secondSqlStatement.setString(3, "sample");
            secondSqlStatement.setString(4, "sample");
            secondSqlStatement.setString(5, "sample");

            secondSqlStatement.executeUpdate();

            //commit the changes
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();//or whatever
            //rollback the changes
            conn.rollback();
        } finally {
            //close the PreparedStatement and the connection if not null
            if (sqlPreparedStatement != null) {
                sqlPreparedStatement.close();
            }
            if (secondSqlStatement != null) {
                secondSqlStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

}
