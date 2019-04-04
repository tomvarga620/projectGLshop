package sk.itsovy.projectGLshop.Database;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {

    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    MongoDatabase database = mongoClient.getDatabase("billDB");
    MongoCollection<Document> collection = database.getCollection("test");

    private static MongoDB mongodb = new MongoDB();

    private MongoDB(){

    }

    public static MongoDB getInstance(){
        return mongodb;
    }

    //TODO
    public void insertNewBillMongo(){


    }

}
