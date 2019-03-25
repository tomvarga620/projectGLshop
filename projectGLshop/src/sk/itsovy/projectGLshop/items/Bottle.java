package sk.itsovy.projectGLshop.items;

import sk.itsovy.projectGLshop.interfaces.Pc;

public class Bottle extends Drink implements Pc {

    private int amount;

    public Bottle(String name, double price, boolean sweet, int amount) {
        super(name, price, sweet);
        this.amount = amount;
    }

    public Bottle(String name,double price,int amount){
        this(name,price,true,amount);
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }

    @Override
    public String getName() {
        return getName();
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
