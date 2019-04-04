package sk.itsovy.projectGLshop.Database;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import sk.itsovy.projectGLshop.bill.Bill;

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

        MongoCollection<org.bson.Document> billCollection = acessCollection();

        System.out.println(billCollection);

        Date date = new java.sql.Date(bill.getDate().getTime());
        Date time = new java.sql.Time(bill.getDate().getTime());



    }

}
