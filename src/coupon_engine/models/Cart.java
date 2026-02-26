package coupon_engine.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {
    private final String id;
    private final String userId;
    private double cartValue = 0.0;
    private final List<Item> items = new ArrayList<>();

    public Cart(String userId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
    }

    public void addItem(Item item) {
        items.add(item);
        cartValue += item.getPrice();
    }

    public double getTotalValue() {
        return cartValue;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getUserId() {
        return userId;
    }
}
