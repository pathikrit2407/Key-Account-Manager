package org.kam.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoWrapperService<T> {

    private final MongoDatabase mongoDatabase;

    public MongoWrapperService() {
        this.mongoDatabase = Dbconnection.getMongoConnection();
    }

    private MongoCollection<T> getCollection(String name, Class<T> clazz) {
       return mongoDatabase.getCollection(name, clazz);
    }

    public void insertOne(String collectionName, T doc, Class<T> clazz) {
        getCollection(collectionName, clazz).insertOne(doc);
    }

    public void insertMany(String collectionName, List<T> docs, Class<T> clazz) {
        getCollection(collectionName, clazz).insertMany(docs);
    }
}
