package models;

import java.time.LocalDateTime;

import enums.DiscountType;

public class Coupon {
    private final String id;
    private final String code;
    private final DiscountType type;
    private final LocalDateTime expiredDate;

    public Coupon(String id, String code, DiscountType type, LocalDateTime expDateTime) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.expiredDate = expDateTime;
    }

    // getters 
    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getExpiredTime() {
        return expiredDate;
    }

    public DiscountType getType() {
        return type;
    }
}
