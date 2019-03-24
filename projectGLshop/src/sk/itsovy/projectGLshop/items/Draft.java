package sk.itsovy.projectGLshop.items;

public class Draft extends Drink implements DrafInterface {

    private double volume;

    public Draft(String name, double price, boolean sweet, double volume) {
        super(name, price, sweet);
        this.volume = volume;
    }

    @Override
    public double getTotalPrice() {
        return volume*getPrice();
    }

    @Override
    public String getName() {
        return getName();
    }

    @Override
    public double getVolume() {
        return volume;
    }

}
