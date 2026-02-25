package rate_limiter.model;

import rate_limiter.enums.UserTier;

public class User {
    private final String userId;
    private final UserTier tier;

    public User(String userId, UserTier tier) {
        this.userId = userId;
        this.tier = tier;
    }
}
