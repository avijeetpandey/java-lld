package coupon_engine.models;

import coupon_engine.enums.Category;

public class Item {
    private final String id;
    private final double price;
    private final Category category;

    public Item(String id, double price, Category category) {
        this.id = id;
        this.price = price;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
