package models;

import java.util.UUID;

public class Order {
    private final String id;
    private final Cart cart;

    public Order(String id, Cart cart) {
        this.id = id;
        this.cart = cart;
    }

    public Cart geCart() {
        return cart;
    }

    public String getId() {
        return id;
    }
}
