package org.kam.db;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoWrapperService<T> {

    private final MongoDatabase mongoDatabase;
    private final Class<T> clazz;

    public MongoWrapperService(Class<T> clazz) {
        this.clazz = clazz;
        this.mongoDatabase = Dbconnection.getMongoConnection();
    }

    private MongoCollection<T> getCollection(String name) {
       return mongoDatabase.getCollection(name, clazz);
    }

    public void insertOne(String collectionName, T doc) {
        getCollection(collectionName).insertOne(doc);
    }

    public void insertMany(String collectionName, List<T> docs) {
        getCollection(collectionName).insertMany(docs);
    }

    public Optional<T> find(String collectionName, Bson filter) {
        FindIterable<T> iterable = getCollection(collectionName).find(filter);
        return Optional.ofNullable(iterable.first());
    }

    public List<T> findAll(String collectionName, Bson filter) {
        FindIterable<T> iterables = getCollection(collectionName).find(filter);
        List<T> data = new ArrayList<>();
        iterables.iterator().forEachRemaining(data::add);
        return data;
    }
}
