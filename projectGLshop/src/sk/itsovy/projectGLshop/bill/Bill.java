package sk.itsovy.projectGLshop.bill;

import sk.itsovy.projectGLshop.Database.Database;
import sk.itsovy.projectGLshop.exception.BillException;
import sk.itsovy.projectGLshop.interfaces.DrafInterface;
import sk.itsovy.projectGLshop.interfaces.Pc;
import sk.itsovy.projectGLshop.items.Fruit;
import sk.itsovy.projectGLshop.items.Item;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static sk.itsovy.projectGLshop.main.Globals.MaxItems;

public class Bill {

    private int id;
    private static int count =0;

    private List<Item> list;
    private boolean open;
    private Date date = new Date(System.currentTimeMillis());

    public Bill() {
       count++;
       this.id = count;
       this.list = new ArrayList<>();
       this.open = true;
    }

    public void addItem(Item item) throws BillException{
        Item tempItem = itemValidation(item);
        if(tempItem==item) {
            if(!open){
                throw new BillException("Cant add more items");
            }
            if (getCount() == MaxItems ) {
                String message = "Bill is full max number of items is "+MaxItems;
                throw new BillException(message);
            }
            else
            {
                list.add(item);
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
                total = total + item.getTotalPrice();
            }
        }

        return total;

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
        /*if(open == true){
            System.out.println("Bill is opened,you can add items");
        }
        else
        {
            System.out.println(dateFormat());
        }*/
    }

    public void billEnd() throws SQLException {

        if(open) {
            date = new Date();
            System.out.println(date);
            Database db = Database.getInstance();
           // Xml xml = new Xml();
            db.insertNewBill(this);
            //xml.createXML(this);
        }

        open=false;
    }

    public String dateFormat(){

        SimpleDateFormat formatter = new SimpleDateFormat("'Datum: 'yyyy.MM.dd 'Cas: 'HH:mm");
        Date date = new Date(System.currentTimeMillis());
        //System.out.println(formatter.format(date));
        return formatter.format(date);
    }

    public Date getDate() {
        return date;
    }

    public List<Item> getList() {
        return list;
    }

    public Item itemValidation(Item item){
        //itemUpdate(item);
        for(Item billitem: list){
            if(item.getName().toLowerCase().equals(billitem.getName().toLowerCase()) &&
            item.getClass().getName().equals(billitem.getClass().getName()) ){
                System.out.println("rovnake");
                return null;
            }
        }return item;
    }

    public void itemUpdate(Item newItem,Item oldItem){

        if(newItem instanceof DrafInterface){
            double rslt = ((DrafInterface) newItem).getVolume() + ((DrafInterface) oldItem).getVolume();
            ((DrafInterface) newItem).setVolume(rslt);

        }else if(newItem instanceof Fruit){
            double rslt = ((Fruit) newItem).getWeight() + ((Fruit) oldItem).getWeight();
            ((Fruit) newItem).setWeight(rslt);

        }else if(newItem instanceof Pc){
            int rslt = ((Pc) newItem).getAmount() + ((Pc) oldItem).getAmount();
            ((Pc) newItem).setAmount(rslt);
        }

    }

    /*
    public double finalPrice(double total){
        DecimalFormat format = new DecimalFormat("##.00");
        return format.format(total.);
    }*/

}
