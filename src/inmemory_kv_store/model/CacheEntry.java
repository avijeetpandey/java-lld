package inmemory_kv_store.model;

public class CacheEntry<V> {
    private final V value;
    private final long expiryTime;

    public CacheEntry(V value, long ttlInMs) {
        this.value = value;
        this.expiryTime = ttlInMs > 0 ? System.currentTimeMillis() + ttlInMs : -1;
    }

    public V getValue() {
        return value;
    }

    public boolean isExpired() {
        if(expiryTime == -1) return false;
        return System.currentTimeMillis() > expiryTime;
    }
}
