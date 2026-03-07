package java_questions.concurrency_and_threading;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
        System.out.println("Starting workflow");

        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            simulateDelay(500);
            System.out.println("Fetched user details");
            return "User_JhonDoe";
        });


        CompletableFuture<String> orderFuture = userFuture.thenApply(userId -> {
            simulateDelay(1000);
            System.out.println("Fetched orders for " + userId);
            return "Laptop, Mouse, Keyboard";
        });

        orderFuture.thenAccept(orders -> {
            System.out.println("The orders are : " + orders);
        });

        simulateDelay(3000);
    }

    public static void simulateDelay(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
