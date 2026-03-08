package producer_consumer_multi_threaded;

import producer_consumer_multi_threaded.repository.InMemoryMessageBroker;
import producer_consumer_multi_threaded.repository.MessageBroker;
import producer_consumer_multi_threaded.service.ConsumerService;
import producer_consumer_multi_threaded.service.ProducerService;

public class MessageBrokerDriverCode {
    public static void main(String[] args) {
        MessageBroker sharedBroker = new InMemoryMessageBroker(5);
        ProducerService productionOne = new ProducerService(sharedBroker, "productionOne");
        ProducerService productionTwo = new ProducerService(sharedBroker, "productionTwo");

        ConsumerService consumerProdOne = new ConsumerService("consumerProdOne", sharedBroker);
        ConsumerService consumerProdTwo = new ConsumerService("consumerProdTwo", sharedBroker);

        // 3. Execute the Services in their own threads
        Thread tp1 = new Thread(productionOne);
        Thread tp2 = new Thread(productionTwo);
        Thread tc1 = new Thread(consumerProdOne);
        Thread tc2 = new Thread(consumerProdTwo);

        System.out.println("Starting Enterprise Pub/Sub Engine...");
        tp1.start();
        tp2.start();
        tc1.start();
        tc2.start();

        try {
            tp1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
