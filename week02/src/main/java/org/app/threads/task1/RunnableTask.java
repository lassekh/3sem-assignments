package org.app.threads.task1;

public class RunnableTask implements Runnable {
    private String name;

    public RunnableTask(String name) {
        this.name = name;
    }
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + ": " + name);
    }
}
