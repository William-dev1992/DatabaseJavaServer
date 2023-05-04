package org.example;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        KafkaConsumerRunnable consumerRunnable = new KafkaConsumerRunnable();
        executor.submit(consumerRunnable);
        Thread.sleep(5);
        executor.shutdown();
    }
}