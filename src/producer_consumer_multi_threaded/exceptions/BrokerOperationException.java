package producer_consumer_multi_threaded.exceptions;

public class BrokerOperationException extends RuntimeException {
    public BrokerOperationException(String message) {
        super(message);
    }
}
