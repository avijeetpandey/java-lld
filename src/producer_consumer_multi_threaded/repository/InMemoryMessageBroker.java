package producer_consumer_multi_threaded.repository;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import producer_consumer_multi_threaded.model.Task;

public class InMemoryMessageBroker implements MessageBroker {
    private final int capacity;
    private final Queue<Task> queue;
    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public InMemoryMessageBroker(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    @Override
    public Task consume() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            Task task = queue.poll();
            notFull.signal();
            return task;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void publish(Task task) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (queue.size() == capacity) {
                notFull.await();
            }
            queue.add(task);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
}
