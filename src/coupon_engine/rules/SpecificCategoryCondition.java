package coupon_engine.rules;

import coupon_engine.enums.Category;
import coupon_engine.models.Cart;
import coupon_engine.models.Item;
import coupon_engine.models.User;

public class SpecificCategoryCondition implements CouponCondition {
    private final Category requiredCategory;

    public SpecificCategoryCondition(Category category) {
        this.requiredCategory = category;
    }

    @Override
    public boolean isSatisfied(Cart cart, User user) {
        for(Item item: cart.getItems()) {
            if(item.getCategory() == requiredCategory) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getErrorMessage() {
        return "Cart must contain atleast one item from category" + requiredCategory;
    }
}
