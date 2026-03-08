package product_listing.models;

import java.util.concurrent.locks.ReentrantLock;

import product_listing.enums.PropertyStatus;

public class Property {
    private final String id;
    private final String name;
    private final double price;
    private final int rooms;
    private final ReentrantLock lock;
    private PropertyStatus status;

    public Property(String id, String name, double price, int rooms, PropertyStatus status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rooms = rooms;
        this.status = status;
        this.lock = new ReentrantLock();
    }

    // getters
    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public int getRooms() {
        return rooms;
    }

    // setters
    public void setPropertyStatus(PropertyStatus updatedStatus) {
        this.status = updatedStatus;
    }

    @Override
    public String toString() {
        return "Property [id=" + id + ", name=" + name + ", price=" + price + ", rooms=" + rooms + "]";
    }
}
