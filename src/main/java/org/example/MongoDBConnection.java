package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;



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

    public void updateCollection(String dataId)  {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", dataId);

        BasicDBObject newStatus = new BasicDBObject();
        newStatus.put("status", "success");

        this.collection.updateOne(query, newStatus);
    }
}
