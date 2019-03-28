package sk.itsovy.projectGLshop.main;

import sk.itsovy.projectGLshop.Application;
import sk.itsovy.projectGLshop.exception.BillException;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws BillException, SQLException {

        Application app = Application.getInstance();
        app.Example();
        /*Internet net = new Internet();
        net.getRequest();*/
    }
}
