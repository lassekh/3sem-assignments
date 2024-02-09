package org.app.threads.task1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExercise {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        /*for (char c = 'A'; c <= 'Z'; c++) {
            String tripleLetter = String.valueOf(c).repeat(3);
            Runnable task = new RunnableTask(tripleLetter);
            executorService.submit(task);
        }
        executorService.shutdown();*/
        Counter counter = new Counter();
        for(int i = 0; i < 1000; i++) {
            executorService.submit(new CounterTask(counter));
        }
        executorService.shutdown();
    }
    private static class Counter {
        private static int count = 0;

        // Method to increment the count, synchronized to ensure thread safety
        public synchronized void increment() {
            count++;
        }

        // Method to retrieve the current count value
        public int getCount() {
            return count;
        }
    }

    private static class CounterTask implements Runnable{
        private Counter counter;
        public CounterTask(Counter counter){
            this.counter = counter;
        }
        @Override
        public void run(){
            counter.increment();
            System.out.println(Thread.currentThread().getName() + ": " + counter.getCount());
        }
    }
}
