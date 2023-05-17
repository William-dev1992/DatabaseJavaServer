package org.example;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

//        KafkaConsumerRunnable registerRunnable = new RegisterRunnable();
        KafkaConsumerRunnable queryRunnable = new QueryRunnable();

//        executor.submit(registerRunnable);
        executor.submit(queryRunnable);

        Thread.sleep(5);
        executor.shutdown();
    }
}