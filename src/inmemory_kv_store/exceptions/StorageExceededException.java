package inmemory_kv_store.exceptions;

public class StorageExceededException extends RuntimeException {
    public StorageExceededException(String message) {
        super(message);
    }
}
