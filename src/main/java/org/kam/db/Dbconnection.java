package org.kam.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.kam.utils.Constants;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Dbconnection {

    private static MongoDatabase mongoDatabase;

    public static MongoDatabase getMongoConnection() {
        if (mongoDatabase == null) {
            //TODO: Move the db connection details into config file
            String connectionString = Constants.DB_CONNECTION_STRING;
            CodecRegistry pojoCodecRegistry = fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );
            MongoClient mongoClient = MongoClients.create(connectionString);
            mongoDatabase = mongoClient
                    .getDatabase(Constants.MONGO_DATABASE)
                    .withCodecRegistry(pojoCodecRegistry);
        }

        return mongoDatabase;
    }
}
