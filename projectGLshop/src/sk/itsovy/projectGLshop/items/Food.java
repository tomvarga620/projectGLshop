package sk.itsovy.projectGLshop.items;

public abstract class Food extends Item {

    private int calories;

    public Food(String name, double price, int calories) {
        super(name, price);
        this.calories = calories;
    }
}
