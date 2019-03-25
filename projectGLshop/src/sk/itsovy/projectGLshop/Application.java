package sk.itsovy.projectGLshop;

import sk.itsovy.projectGLshop.bill.Bill;
import sk.itsovy.projectGLshop.items.*;

import static sk.itsovy.projectGLshop.items.Category.School;

public class Application {

    public void Example(){

        Bill bill = new Bill();

        Bottle milk = new Bottle("Milk 1,5%",0.59,1);
        Item pizza = new Pastry("Pizza",1.10,280,2);
        Food apple = new Fruit("Apple",0.50,80,300);
        Item pencil = new Goods("Pencil",2,1,School);
        Item kofola = new Draft("kofola",0.80,true,0.3);
        Item beer = new Bottle("Beer 12%",2,true,1);

        bill.addItem(milk);
        bill.addItem(pizza);
        bill.addItem(apple);
        bill.addItem(pencil);
        bill.addItem(kofola);
        bill.addItem(beer);

        bill.removeItem(beer);

        int count = bill.getCount();

    }

}
