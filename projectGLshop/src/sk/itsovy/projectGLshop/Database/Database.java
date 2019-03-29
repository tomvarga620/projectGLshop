package sk.itsovy.projectGLshop.Database;

import sk.itsovy.projectGLshop.bill.Bill;
import sk.itsovy.projectGLshop.interfaces.DrafInterface;
import sk.itsovy.projectGLshop.interfaces.Pc;
import sk.itsovy.projectGLshop.items.Fruit;
import sk.itsovy.projectGLshop.items.Item;
import sk.itsovy.projectGLshop.main.Globals;

import java.sql.*;

public class Database {

    private static Database db = new Database();

    private Database(){

    }

    public static Database getInstance(){
        return db;
    }

    public void insertNewBill(Bill bill) throws SQLException {
        Connection conn = Globals.getConnection();
        try {
            //set connection auto commit to false
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Bill (date,totalPrice,time) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            //set all the values in the prepared statement
            statement.setDate(1, new java.sql.Date(bill.getDate().getTime()));
            statement.setFloat(2, (float)bill.getFinalPrice());
            statement.setTime(3,new java.sql.Time(bill.getDate().getTime()));

            int rslt = statement.executeUpdate();

            if (rslt == 0){

                throw new SQLException("Creating user failed, no rows affected.");

            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()){

                if(generatedKeys.next()) {
                    //set all the values in the prepared statement
                    for (Item item : bill.getList()) {

                        PreparedStatement statement2 = conn.prepareStatement("INSERT INTO Item (OrderID,Name,Price,Count,Unit) VALUES (?,?,?,?,?)");
                        statement2.setDouble(1,generatedKeys.getLong(1));
                        statement2.setString(2,item.getName());
                        statement2.setFloat(3,(float)item.getPrice());

                        if (item instanceof DrafInterface) {
                            statement2.setDouble(4,((DrafInterface) item).getVolume());
                            statement2.setString(5,"l");
                        } else if (item instanceof Fruit) {
                            statement2.setDouble(4,((Fruit) item).getWeight());
                            statement2.setString(5,"Kg");
                        } else if (item instanceof Pc) {
                            statement2.setDouble(4,((Pc) item).getAmount());
                            statement2.setString(5,"Pcs");
                        }

                        statement2.executeUpdate();
                    }

                }
                else
                {
                    throw new SQLException("Creating bill failed, no ID obtained.");
                }
            }
            //commit the changes
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            //rollback the changes
            conn.rollback();
        }

    }

}
