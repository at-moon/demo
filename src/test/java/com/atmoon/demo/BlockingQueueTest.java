package com.atmoon.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : zy
 */
public class BlockingQueueTest {

    private static BlockingQueue<Integer> queue;

    public static void main(String[] args) {
        queue = new ArrayBlockingQueue<>(10);
        Provider provider = new Provider();
        Consumer consumer = new Consumer();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(provider);
        service.execute(consumer);
    }

    static class Provider implements Runnable {

        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    queue.put(i);
                    Thread.sleep(1000);
                    System.out.println("provide " + i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    int i = queue.take();
                    Thread.sleep(1000);
                    System.out.println("consume " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
