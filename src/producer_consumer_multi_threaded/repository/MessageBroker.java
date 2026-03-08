package producer_consumer_multi_threaded.repository;

import producer_consumer_multi_threaded.model.Task;

public interface MessageBroker {
    void publish(Task task) throws InterruptedException;
    Task consume() throws InterruptedException;
}
