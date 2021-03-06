package sk.itsovy.projectGLshop.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Globals {

    public static final int MaxItems;
    public static final String username="customer";
    public static final String password="glshop";
    public static final String host = "localhost";
    public static final String port = "3306";
    public static final String url = "jdbc:mysql://localhost:3306/GLshop";

    //mongoDB

    public static final String portMongo = "27017";
    public static final String hostMongo = "localhost";

    public static final String userMongo= "root";
    public static final String passMong = "admin";
    public static final String dbname = "billDB";

    static {
        MaxItems = 8;
    }

    public static Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DriverLoaded");
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}
