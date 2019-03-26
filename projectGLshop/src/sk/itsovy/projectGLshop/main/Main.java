package sk.itsovy.projectGLshop.main;

import sk.itsovy.projectGLshop.Application;
import sk.itsovy.projectGLshop.exception.BillException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws BillException {

        Application app = Application.getInstance();
        app.Example();
    }
}
