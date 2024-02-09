package org.app.threads.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AddingNumbers {

    public static void main(String[] args) {
        //Task fixed by setting list to a synchronizedList
        ExecutorService workingJack = Executors.newFixedThreadPool(17);

        IntegerList integerList = new IntegerList();
        for (int count = 0; count < 100; count++) {
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
                Thread.sleep((int) Math.random()*800+200);
                integerList.addCount(count);
            } catch (InterruptedException ex) {
                System.out.println("Thread was interrupted");
            }
        }
    }
}

