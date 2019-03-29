package sk.itsovy.projectGLshop.items;

import sk.itsovy.projectGLshop.interfaces.ItemInterface;

public abstract class Item implements ItemInterface {

    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getPrice() {

        return price;
    }

}


