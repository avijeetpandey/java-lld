package models;

import java.util.List;

import rules.DiscountRule;

public class Cart {
    private final String id;
    private final String userName;
    private final String userId;
    private final List<Product> products;
    private final String couponCode;
    private double totalCartAmount;
    private final DiscountRule rule;

    public Cart(String id, String userName, String userId, List<Product> products, String couponCode,
            double totalCartAmount, DiscountRule rule) {
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.products = products;
        this.couponCode = couponCode;
        this.totalCartAmount = totalCartAmount;
        this.rule = rule;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public double getTotalCartAmount() {
        return totalCartAmount;
    }

    public DiscountRule getRule() {
        return rule;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double applyDiscount() {
        return rule.applyDiscount(totalCartAmount);
    }

    public void updateCartAmount() {
        for(Product product: products) {
            totalCartAmount += product.getPrice();
        }
    }
}
