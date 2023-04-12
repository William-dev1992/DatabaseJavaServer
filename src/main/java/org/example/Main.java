package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static org.example.KafkaConsumer.getKafkaConsumer;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
//        KafkaProducer<String,String> producer = getKafkaProducer();
//
//        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("server-events", "hello world");
//        producer.send(producerRecord);
//        producer.flush();
//        producer.close();

        KafkaConsumer<String,String> consumer = getKafkaConsumer();

        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println("message: " + record.value());
        }
        consumer.commitSync();
    }

    public static KafkaProducer<String,String> getKafkaProducer() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<String, String>(properties);
    }
}