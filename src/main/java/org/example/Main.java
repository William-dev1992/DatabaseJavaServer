package org.example;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        MongoDBConnection connection = new MongoDBConnection("datas");

//        ExecutorService executor = Executors.newFixedThreadPool(10);
//
//        KafkaConsumerRunnable consumerRunnable = new KafkaConsumerRunnable();
//        executor.submit(consumerRunnable);
//        Thread.sleep(5);
//        executor.shutdown();
    }
}