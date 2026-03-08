package producer_consumer_multi_threaded.service;

import producer_consumer_multi_threaded.exceptions.BrokerOperationException;
import producer_consumer_multi_threaded.model.Task;
import producer_consumer_multi_threaded.repository.MessageBroker;

public class ProducerService implements Runnable {
    private final MessageBroker broker;
    private final String producerName;

    public ProducerService(MessageBroker broker, String producerName) {
        this.broker = broker;
        this.producerName = producerName;
    }

    @Override
    public void run() {
        int taskCount = 1;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Task task = new Task("task"+taskCount, "Payload " + taskCount++);
                broker.publish(task);
                System.out.println("Published task with taskId " + task.getTaskId());
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                throw new BrokerOperationException("Unexpected error in producer " + producerName);
            }
        }
    }
}
