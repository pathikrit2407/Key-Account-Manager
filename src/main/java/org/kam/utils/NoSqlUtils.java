package org.kam.utils;

import com.mongodb.client.MongoDatabase;
import org.kam.db.Dbconnection;
import org.kam.dtos.CallDto;

import java.time.LocalDate;

/*
*       NoSqlUtils is an utility class for testing db related queries
* */

public class NoSqlUtils {

    public static void main(String[] args) {
        MongoDatabase mongoDatabase = Dbconnection.getMongoConnection();
        mongoDatabase.createCollection("test");

        CallDto callDto = new CallDto();
        callDto.setId(100);
        callDto.setKamId(1);
        callDto.setCallDate(LocalDate.now());
        callDto.setContactDetailsId(11);

        /*
           Gson gson = new GsonBuilder()
           .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
           .create();
           Document document = Document.parse(gson.toJson(callDto));

           Gson is used to parse POJOs into Json so that it can be parsed into Bson's Document.
           But how Bson internally implements many data types such as localdate is an open issue
           for which a lot of manual serialiser and deserialser needs to be written.

           To avoid that we made changes in the Dbconnection class, which now takes a codecRegistry.
         */

        mongoDatabase.getCollection("test", CallDto.class).insertOne(callDto);
    }
}
