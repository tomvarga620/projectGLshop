package sk.itsovy.projectGLshop.Database;

import sk.itsovy.projectGLshop.bill.Bill;
import sk.itsovy.projectGLshop.main.Globals;

import java.sql.Connection;

public class Database {

    private static Database db = new Database();

    private Database(){

    }

    public static Database getInstance(){
        return db;
    }

    public void insertNewBill(Bill bill){
        Connection conn = Globals.getConnection();


    }

}
