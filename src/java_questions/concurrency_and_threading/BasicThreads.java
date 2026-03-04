package java_questions.concurrency_and_threading;

public class BasicThreads {
    public static void main(String[] args) {
        Runnable myTask = () -> {
            System.out.println("Task is running in thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(myTask,"newThread");

        // start the thread manually
        thread.start();
    }
}
