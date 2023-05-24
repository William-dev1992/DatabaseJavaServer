package org.example;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.bson.conversions.Bson;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class QueryRunnable extends KafkaConsumerRunnable{

    public QueryRunnable() {
        super();
        this.consumer = new KafkaConsumer<String, String>(this.properties);
        this.consumer.subscribe(Collections.singleton("server-queries"));
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<String, String> records = this.consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                String queryId = JsonPath.read(value, "$.queryId");
                Map queryValue = JsonPath.read(value, "$.queryValue");
                System.out.println(queryId);
                this.mongoConnection.executeQuery(queryId, queryValue);
            }
            this.consumer.commitSync();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
