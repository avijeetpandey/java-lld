package producer_consumer_multi_threaded.service;

import producer_consumer_multi_threaded.exceptions.BrokerOperationException;
import producer_consumer_multi_threaded.model.Task;
import producer_consumer_multi_threaded.repository.MessageBroker;

public class ConsumerService implements Runnable {
    private final String consumerName;
    private final MessageBroker broker;

    public ConsumerService(String consumerName, MessageBroker broker) {
        this.broker = broker;
        this.consumerName = consumerName;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Task task = broker.consume();
                if (task == null) {
                    System.out.println("WARNING: Consumed a null task!");
                } else {
                    System.out.println("Processing task " + task.getTaskId());
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                throw new BrokerOperationException(
                        "Something wrong with the consumer " + e.getLocalizedMessage() + consumerName);
            }
        }
    }
}
