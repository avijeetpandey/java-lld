package models;

import java.util.concurrent.locks.ReentrantLock;

public class Product {
    private final String id;
    private final String name;
    private Integer availableQuantity;
    private ReentrantLock lock;
    private long lockedAt;

    public Product(String id, String name , Integer availableQuantity) {
        this.id = id;
        this.name = name;
        this.availableQuantity = availableQuantity;
        this.lockedAt = -1;
        this.lock = new ReentrantLock();
    }

    // getters and setters
    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setLockedAt() {
        this.lockedAt = System.currentTimeMillis();
    }

    public long getLockedAt() {
        return lockedAt;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}