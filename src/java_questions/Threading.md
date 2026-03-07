# Concurrency

Concurreny is all about managing multiple tasks at once, for example a chef can cook , cut vegetables and fry other things very quicky but at any exact given moment, he is doing one task at a time very quickly.

## Parallelism

Parallelism means executing multiple things at once, at the same time.

The way to achieve concurrency and parallelism in Java and they are
- Basic threads
- Via Executor framework
- CompletableFuture
- Via virtual threads ( Java21 )


## Basic threading 
In the older versions of java if some background task needs to be done and executed it was a manual process and we have to create threads and manage it manually, every JAVA thread is mapped directly to the OS level thread , which made them heavy and took lot of memory and time to startup.

```java
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
```

## Executor framework
In order to overcome the old way of dealing with threads , Java5 introduced Executor framework which gives pool of re-usable threads and if lets say 4 threads are available in the pool , 4 will be used and rest of the tasks will wait in queue untill finished.

```java
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
```

## Completable future
Completable Future was introduced in Java8 , as dealing with complex tasks was still cumbersome so in order to chain the tasks asyn way and without blocking the main thread , CompletableFuture was created