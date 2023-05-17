package org.example;

import com.jayway.jsonpath.JsonPath;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;

public class RegisterRunnable extends KafkaConsumerRunnable{

    public RegisterRunnable() {
        super();
        this.consumer = new KafkaConsumer<String, String>(this.properties);
        this.consumer.subscribe(Collections.singleton("server-registers"));
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<String, String> records = this.consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                String itemId = JsonPath.read(value, "$.id");
                System.out.println(itemId);
                this.mongoConnection.updateCollectionStatus(itemId);
            }
            this.consumer.commitSync();

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
