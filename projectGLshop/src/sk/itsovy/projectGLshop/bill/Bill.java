package sk.itsovy.projectGLshop.bill;

import sk.itsovy.projectGLshop.Application;
import sk.itsovy.projectGLshop.items.Item;

import java.util.List;

public class Bill extends Application {

    private List<Item> list;

    public Bill(List<Item> list) {
        this.list = list;
    }

    public void addItem(Item item){
        list.add(item);
    }

    public void removeItem(Item item){
        list.remove(item);
    }

    public double getFinalPrice(){
        throw new UnsupportedOperationException("Method does not exist yet");
    }
}
