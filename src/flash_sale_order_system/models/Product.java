package flash_sale_order_system.models;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import flash_sale_order_system.strategy.PricingStrategy;

public class Product {
    private final String id;
    private final String name;
    private final double basePrice;
    private int availableInventory;
    private final PricingStrategy pricingStrategy;

    private final Set<String> successfullBuyers;
    private final ReentrantLock lock;

    public Product(String name, double basePrice, int availableInventory, PricingStrategy pricingStrategy) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.basePrice = basePrice;
        this.availableInventory = availableInventory;
        this.pricingStrategy = pricingStrategy;
        this.successfullBuyers = ConcurrentHashMap.newKeySet();
        this.lock = new ReentrantLock();
    }

    // getters 
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // util functions
    public double getFinalPrice() {
        return pricingStrategy.calculatePrice(basePrice);
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public Set<String> getSuccessfullBuyers() {
        return successfullBuyers;
    }

    public void decrementInventory() {
        availableInventory--;
    }

    public int getAvailableInventory() {
        return availableInventory;
    }
}
