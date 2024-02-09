package org.app.threads.task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UtilizingSystemCores {

    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService workingJack = Executors.newFixedThreadPool(cores);

        IntegerList integerList = new IntegerList();
        for (int count = 0; count < 1000000; count++) {
            workingJack.submit(new TaskToAddCount(integerList, count));
        }
        workingJack.shutdown();

    }

    private static class IntegerList {
        private static List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        public void addCount(int count) {
            list.add(count);
            System.out.println("Task: " + count + ": List size = " + list.size());
        }
    }
    private static class TaskToAddCount implements Runnable {
        // Gets a reference to the shared list and the count to add
        private IntegerList integerList;
        private int count;

        TaskToAddCount(IntegerList integerList, int count) {
            this.integerList = integerList;
            this.count = count;
        }

        @Override
        public void run() {
            try {
                integerList.addCount(count);
            } catch (Exception ex) {
                System.out.println("Thread was interrupted");
            }
        }
    }
}

