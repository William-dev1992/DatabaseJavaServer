package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import com.jayway.jsonpath.JsonPath;

public class KafkaConsumerRunnable implements Runnable {
    KafkaConsumer<String,String> consumer;
    MongoDBConnection mongoConnection;

    public KafkaConsumerRunnable () {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "server-events");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        this.consumer = new KafkaConsumer<String, String>(properties);
        this.consumer.subscribe(Collections.singleton("server-events"));

        this.mongoConnection = new MongoDBConnection("datas");
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
