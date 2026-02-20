package cache;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import cache.datasource.Datasource;
import cache.enums.EvictionAlgorithm;
import cache.enums.PersistanceAlgorithm;

public class Cache<KEY, VALUE> {
    // properties
    private final Datasource<KEY, VALUE> datasource;
    private final Map<KEY, VALUE> map;
    private final EvictionAlgorithm evictionAlgorithm;
    private final PersistanceAlgorithm persistanceAlgorithm;
    private static final Integer THRESHOLD_SIZE = 100;

    public Cache(Datasource<KEY, VALUE> datasource, EvictionAlgorithm evictionAlgorithm,
            PersistanceAlgorithm persistanceAlgorithm) {
        this.datasource = datasource;
        this.evictionAlgorithm = evictionAlgorithm;
        this.map = new ConcurrentHashMap<>();
        this.persistanceAlgorithm = persistanceAlgorithm;
    }

    public Future<VALUE> get(KEY key) {
        if (map.containsKey(key)) {
            return CompletableFuture.completedFuture(map.get(key));
        } else {
            return datasource.get(key);
        }
    }

    public Future<Void> set(KEY key, VALUE value) {
        if (!map.containsKey(key) && map.size() > THRESHOLD_SIZE) {
            if (evictionAlgorithm.equals(EvictionAlgorithm.LRU)) {

            } else {

            }
        }

        // 
        if (persistanceAlgorithm.equals(PersistanceAlgorithm.WRITE_THROUGH)) {
            return datasource.persist(key, value).thenAccept(_ -> map.put(key, value));
        } else {
            map.put(key, value);
            datasource.persist(key, value);
            return CompletableFuture.completedFuture(null);
        }
    }
}