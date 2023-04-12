package org.example;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.net.UnknownHostException;
import java.time.Duration;

import static org.example.KafkaConsumer.getKafkaConsumer;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        KafkaConsumer<String,String> consumer = getKafkaConsumer();

        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println("message: " + record.value());
        }
        consumer.commitSync();
    }
}