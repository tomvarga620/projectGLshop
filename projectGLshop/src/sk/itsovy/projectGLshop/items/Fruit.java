package sk.itsovy.projectGLshop.items;

public class Fruit extends Food {

    private double weight;

    public Fruit(String name, double price, int calories, double weight) {
        super(name, price, calories);
        this.weight = weight;
    }

    @Override
    public double getTotalPrice() {
        return weight*getPrice();
    }

    @Override
    public String getName() {
        return getName();
    }

    public double getWeight() {
        return weight;
    }
}
