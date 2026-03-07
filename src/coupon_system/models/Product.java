package models;

import java.util.concurrent.locks.ReentrantLock;

public class Product {
    private final String id;
    private final String name;
    private final double price;
    private int quantity;
    private final ReentrantLock lock;


    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.lock = new ReentrantLock();
    }

    // getters
    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }
    
    public void decrementQuantity() {
        this.quantity--;
    }
}
