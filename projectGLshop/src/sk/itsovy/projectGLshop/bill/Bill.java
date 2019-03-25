package sk.itsovy.projectGLshop.bill;

import sk.itsovy.projectGLshop.Application;
import sk.itsovy.projectGLshop.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Bill extends Application {

    private List<Item> list;

    public Bill() {
       this.list = new ArrayList<>();
    }

    public void addItem(Item item){

        list.add(item);
        if(item!=null) {
            if (getCount() == 7) {
                throw new StackOverflowError("Bill is full");
            }
        }
    }

    public void removeItem(Item item){
        list.remove(item);
    }

    public double getFinalPrice(){
        throw new UnsupportedOperationException("Method does not exist yet");
    }

    public int getCount(){
        return this.list.size();
    }
}
