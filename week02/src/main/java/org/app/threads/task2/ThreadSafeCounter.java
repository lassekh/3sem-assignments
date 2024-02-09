package org.app.threads.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeCounter {
    public static void main(String[] args) {
        Counter counter = new Counter();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for(int i = 0; i < 1000; i++){
            Runnable task = () -> {
                counter.increment();
                System.out.println(Thread.currentThread().getName() + "---- Count: " + counter.getCount());
            };
            executorService.submit(task);
        }
        executorService.shutdown();
    }
    private static class Counter {
        private int count = 0;

        // Method to increment the count, synchronized to ensure thread safety
        public synchronized void increment() {
            count++;
        }

        // Method to retrieve the current count value
        public int getCount() {
            return count;
        }
    }
}


