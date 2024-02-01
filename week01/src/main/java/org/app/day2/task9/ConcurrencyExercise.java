package org.app.day2.task9;

import java.util.concurrent.CompletableFuture;

public class ConcurrencyExercise {
    public static void main(String[] args) {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> new Task().run());
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> new Task().run());
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> new Task().run());
        CompletableFuture<Void> allF = CompletableFuture.allOf(future1,future2,future3);
        allF.thenRun(() -> System.out.println("All futures completed"));
    }
}
