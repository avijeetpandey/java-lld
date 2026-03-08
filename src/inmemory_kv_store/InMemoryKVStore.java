package inmemory_kv_store;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import inmemory_kv_store.exceptions.KeyNotFoundException;
import inmemory_kv_store.exceptions.StorageExceededException;
import inmemory_kv_store.model.CacheEntry;

public class InMemoryKVStore<K, V> {
    private final ConcurrentHashMap<K, CacheEntry<V>> store;
    private final int capacity;
    private final ScheduledExecutorService cleanupExecutor;

    private InMemoryKVStore(int capacity, long cleanupTimeInMs) {
        this.capacity = capacity;
        this.store = new ConcurrentHashMap<>(capacity);
        this.cleanupExecutor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "cleanupDaemon");
            t.setDaemon(true);
            return t;
        });

        // scheduling
        this.cleanupExecutor.scheduleAtFixedRate(this::purgeExpiredKeys, cleanupTimeInMs, cleanupTimeInMs,
                TimeUnit.MILLISECONDS);
    }

    public void put(K key, V value, long ttlInMs) {
        if (store.size() >= capacity && !store.containsKey(key)) {
            throw new StorageExceededException("KV store is at max capacity");
        }

        store.put(key, new CacheEntry<V>(value, ttlInMs));
    }

    public V get(K key) {
        CacheEntry<V> entry = store.get(key);

        if (entry == null) {
            throw new KeyNotFoundException("Key not found");
        }

        if (entry.isExpired()) {
            store.remove(key);
            throw new KeyNotFoundException("Key not found");
        }

        return entry.getValue();
    }

    public void delete(K key) {
        if (store.contains(key)) {
            store.remove(key);
        } else {
            throw new KeyNotFoundException("Unable to delete, key not present");
        }
    }

    private void purgeExpiredKeys() {
        int removedCount = 0;
        for (K key : store.keySet()) {
            CacheEntry<V> entry = store.get(key);
            if (entry.isExpired()) {
                store.remove(key);
                removedCount++;
            }
        }

        if (removedCount > 0) {
            System.out.println("Removed: " + removedCount + "inactive keys");
        }
    }

    public void shutDown() {
        cleanupExecutor.shutdown();
    }

    // builder
    public static class Builder<K, V> {
        private int capacity = 100;
        private long cleanupInterval = 5000;

        public Builder<K, V> capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder<K, V> cleanupIntervalMs(long interval) {
            this.cleanupInterval = interval;
            return this;
        }

        public InMemoryKVStore<K, V> build() {
            return new InMemoryKVStore<K, V>(capacity, cleanupInterval);
        }
    }
}
