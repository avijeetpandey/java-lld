package models;

import java.util.ArrayList;
import java.util.List;

import strategy.PaymentStrategy;

public class Order {
    private final String id;
    private final PaymentStrategy strategy;
    private List<String> productsInOrder;

    public Order(String id, PaymentStrategy strategy) {
        this.strategy = strategy;
        this.id = id;
        this.productsInOrder = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void checkOut(double amount) {
        strategy.pay(amount);
    }

    public void setProductsInOrder(List<String> products) {
        if (!this.productsInOrder.isEmpty()) {
            this.productsInOrder.addAll(products);
        }
    }
}
