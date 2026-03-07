package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final String id;
    private final String name;
    private List<String> usedCoupons = new ArrayList<>();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public List<String> getUsedCoupons() {
        return usedCoupons;
    }

    public void setUsedCoupons(List<String> usedCoupons) {
        this.usedCoupons = usedCoupons;
    }
}
