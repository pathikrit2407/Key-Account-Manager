package org.kam.db;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<T> find(String collectionName, Bson filter, Class<T> clazz) {
        FindIterable<T> iterable = getCollection(collectionName, clazz).find(filter);
        return Optional.ofNullable(iterable.first());
    }

    public List<T> findAll(String collectionName, Bson filter, Class<T> clazz) {
        FindIterable<T> iterables = getCollection(collectionName, clazz).find(filter);
        List<T> data = new ArrayList<>();
        iterables.iterator().forEachRemaining(data::add);
        return data;
    }
}
