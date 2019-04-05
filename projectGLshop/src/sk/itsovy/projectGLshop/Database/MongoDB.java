package sk.itsovy.projectGLshop.Database;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sk.itsovy.projectGLshop.bill.Bill;
import sk.itsovy.projectGLshop.items.Item;

import java.sql.Time;
import java.util.Date;

import static sk.itsovy.projectGLshop.main.Globals.*;

public class MongoDB {
/*
    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    MongoDatabase database = mongoClient.getDatabase("billDB");
    MongoCollection<Document> collection = database.getCollection("bills");
*/
    private static MongoDB mongodb = new MongoDB();

    private MongoDB(){

    }

    public static MongoDB getInstance(){
        return mongodb;
    }

    public MongoDatabase mongoConnect () {
        MongoDatabase database = null;
        try
        {
            MongoClient mongoClient = new MongoClient(hostMongo,Integer.parseInt(portMongo));
            // Creating Credentials
            MongoCredential credential;
            credential = MongoCredential.createCredential(userMongo,dbname,
                    "password".toCharArray());
            database = mongoClient.getDatabase(dbname);

            for (String name : database.listCollectionNames()) {

                System.out.println(name);
            }

            //mongoClient.close();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return database;
    }

    public MongoCollection<org.bson.Document> acessCollection(){

        MongoDatabase database = mongoConnect();

        MongoCollection<org.bson.Document> billCollection = database.getCollection("bill");

        return billCollection;

    }

    //TODO
    public void insertNewBillMongo(Bill bill){

        MongoCollection<Document> billCollection = acessCollection();

        System.out.println(billCollection);

        Date date = new java.sql.Date(bill.getDate().getTime());
        Date time = new Time(bill.getDate().getTime());

        //dokumenty pre udaje
        Document documentBill = new Document();
        Document documentDate = new Document();
        Document documentItems = new Document();

        //String title = "test";

        //pridanie datumu a casu do documentDate
        documentDate.append("PurchaseDate", date);
        documentDate.append("PurchaseTime",time);

        //pridane dokumentu do documentBill
        documentBill.append("Date",documentDate);

        for(Item item: bill.getList()){
            Document documentItem = new Document("name",item.getName());
            documentItems.append("Item",documentItem);
        }

        documentBill.append("Items",documentItems);


        billCollection.insertOne(documentBill);

    }

}
