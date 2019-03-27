package sk.itsovy.projectGLshop.main;

import sk.itsovy.projectGLshop.Internet;
import sk.itsovy.projectGLshop.exception.BillException;

public class Main {

    public static void main(String[] args) throws BillException {

       /*Application app = Application.getInstance();
        app.Example();*/
        Internet net = new Internet();
        net.getRequest();

    }
}
