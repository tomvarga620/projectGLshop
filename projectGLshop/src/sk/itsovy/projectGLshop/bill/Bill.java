package sk.itsovy.projectGLshop.bill;

import sk.itsovy.projectGLshop.Application;
import sk.itsovy.projectGLshop.exception.BillException;
import sk.itsovy.projectGLshop.interfaces.DrafInterface;
import sk.itsovy.projectGLshop.interfaces.Pc;
import sk.itsovy.projectGLshop.items.Draft;
import sk.itsovy.projectGLshop.items.Fruit;
import sk.itsovy.projectGLshop.items.Item;

import java.util.ArrayList;
import java.util.List;

import static sk.itsovy.projectGLshop.main.Globals.MaxItems;

public class Bill extends Application {

    private List<Item> list;

    public Bill() {
       this.list = new ArrayList<>();
    }

    public void addItem(Item item) throws BillException{

        list.add(item);
        if(item!=null) {
            if (getCount() == MaxItems ) {
                String message = "Bill is full max number of items is "+MaxItems;
                throw new BillException(message);
            }
        }
    }

    public void removeItem(Item item){
        list.remove(item);
    }

    public double getFinalPrice(){
        double total=0;
        if(getCount()==0){
            return 0;
        }
        else
        {
            for(Item item: list){
                if(item instanceof DrafInterface){
                    total = total + item.getTotalPrice();
                }else if(item instanceof Fruit){
                    total = total + item.getTotalPrice();
                }else if(item instanceof Pc){
                    total = total + item.getTotalPrice();
                }
            }
        }

        return total;

        //throw new UnsupportedOperationException("Method does not exist yet");
    }

    public int getCount(){
        return this.list.size();
    }

    public void printAll(){
        if(getCount()==0){
            System.out.println("Nothing to print Bill is empty");
        }
        else
        {
            for(Item item: list){
                if(item instanceof DrafInterface){
                    System.out.println(item.getName()+" "+((DrafInterface) item).getVolume());
                    System.out.println(item.getPrice()+" "+item.getTotalPrice());
                }else if(item instanceof Fruit){
                    System.out.println(item.getName()+" "+((Fruit) item).getWeight());
                    System.out.println(item.getPrice()+" "+item.getTotalPrice());
                }else if(item instanceof Pc){
                    System.out.println(item.getName()+" "+((Pc) item).getAmount());
                    System.out.println(item.getPrice()+" "+item.getTotalPrice());
                }
            }
        }
    }
}
