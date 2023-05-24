package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;


public class MongoDBConnection extends BasicDBObject {
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    private final MongoCollection<Document> collection;
    public MongoDBConnection (String collectionName) {
        // TODO: Handle try catch auto close.
        this.mongoClient = MongoClients.create("mongodb+srv://williamferreira132003:DbProjectPass@databasecluster.wnqh0gs.mongodb.net/develop?retryWrites=true&w=majority");
        this.database = this.mongoClient.getDatabase("develop");
        this.collection = this.database.getCollection(collectionName);
    }

    public MongoCursor<Document> queryCollection(Bson query) {
        return this.collection.find(query).cursor();
    }

    public void updateCollectionStatus(String dataId)  {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", dataId);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("status", 2);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        this.collection.updateOne(query, updateObject);
    }

    public void executeQuery(String queryId, Map<String, Object> query) {
        Document filter = new Document();
        for (Map.Entry<String,Object> entry : query.entrySet()) {
            filter.append(entry.getKey(), entry.getValue());
        }

        FindIterable<Document> result = this.collection.find(filter);

        System.out.println(result);
        this.updateCollectionResult(queryId, result);
    }

    private void updateCollectionResult(String queryId, Object queryResult)  {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", queryId);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("result", queryResult);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        this.collection.updateOne(query, updateObject);
    }
}
