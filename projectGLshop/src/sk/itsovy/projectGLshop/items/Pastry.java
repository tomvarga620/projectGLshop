package sk.itsovy.projectGLshop.items;

public class Pastry extends Food implements Pc {

    private int amount;

    public Pastry(String name, double price, int calories, int amount) {
        super(name, price, calories);
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public String getName() {
        return getName();
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }
}
