package sk.itsovy.projectGLshop;

import sk.itsovy.projectGLshop.bill.Bill;
import sk.itsovy.projectGLshop.exception.BillException;
import sk.itsovy.projectGLshop.items.*;

import java.sql.SQLException;

import static sk.itsovy.projectGLshop.items.Category.School;

public class Application {

    private static Application app = new Application();

    private Application(){

    }

    public static Application getInstance(){
        return app;
    }

    public void Example() throws BillException, SQLException {

        Internet net = new Internet();
        net.getRequest();

        Xml xml = new Xml();

        Bill bill = new Bill();

        Bottle milk = new Bottle("Milk 1,5%",0.59,1);
        Item pizza = new Pastry("Pizza",1.10,280,2);
        Food apple = new Fruit("Apple",0.50,80,300);
        Item pencil = new Goods("Pencil",2,1,School);
        Item kofola = new Draft("kofola",0.80,true,0.3);
        Item beer = new Bottle("Beer 12%",2,true,1);
        Item b1 = new Bottle("Beer <12%",2,true,1 );

        bill.addItem(milk);
        bill.addItem(pizza);
        bill.addItem(apple);
        bill.addItem(pencil);
        bill.addItem(kofola);
        bill.addItem(beer);
        bill.addItem(b1);

        int count = bill.getCount();
        System.out.println(count);
        bill.billEnd();
        bill.printAll();
       //xml.createXML(bill);
        System.out.println("\n");
        System.out.println(bill.getFinalPrice());
        System.out.println(net.getFinalToUSD(bill.getFinalPrice()));

    }

}
