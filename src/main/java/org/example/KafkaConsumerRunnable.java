package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerRunnable implements Runnable {

    KafkaConsumer<String,String> consumer;
    MongoDBConnection mongoConnection;

    Properties properties;

    public KafkaConsumerRunnable () {
        this.properties = new Properties();
        this.properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        this.properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        this.properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        this.properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "server-events");
        this.properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        this.mongoConnection = new MongoDBConnection("datas");
    }

    public void run() {
        try {
            throw new Exception();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
