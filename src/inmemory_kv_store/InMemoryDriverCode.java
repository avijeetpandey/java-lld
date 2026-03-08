package inmemory_kv_store;

import inmemory_kv_store.exceptions.KeyNotFoundException;

public class InMemoryDriverCode {
    public static void main(String[] args) throws InterruptedException {
        InMemoryKVStore<String, String> kvStore = new InMemoryKVStore.Builder<String, String>()
                .capacity(100)
                .cleanupIntervalMs(2000).build();

        // 1. Put persistent and volatile data
        kvStore.put("user_1", "Alice", -1); // Lives forever
        kvStore.put("session_123", "Active", 3000); // 3-second TTL

        // 2. Immediate retrieval
        System.out.println("User 1: " + kvStore.get("user_1"));
        System.out.println("Session 123: " + kvStore.get("session_123"));

        // 3. Wait for TTL to expire (simulate time passing)
        System.out.println("\nWaiting 4 seconds for TTL to expire...\n");
        Thread.sleep(4000);

        // 4. Test the evictions
        try {
            System.out.println("Session 123 (After 4s): " + kvStore.get("session_123"));
        } catch (KeyNotFoundException e) {
            System.err.println("Caught Expected Error: " + e.getMessage());
        }

        System.out.println("User 1 (After 4s): " + kvStore.get("user_1")); // Still exists

        // Clean up thread pool before exiting
        kvStore.shutDown();
    }
}
