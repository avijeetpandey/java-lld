package java_questions.concurrency_and_threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // handing over tasks to the thread pool
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            // handing over here
            executorService.submit(() -> {
                System.out.println("Executing task " + taskId + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // close the executors
        executorService.shutdown();
        System.out.println("All tasks are submitted thread pool is free");
    }
}
