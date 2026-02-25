package rate_limiter.service;

import java.util.HashMap;
import java.util.Map;

import rate_limiter.enums.RateLimiterType;
import rate_limiter.enums.UserTier;
import rate_limiter.factory.RateLimiterFactory;
import rate_limiter.limiter.RateLimiter;
import rate_limiter.model.RateLimitConfig;
import rate_limiter.model.User;

public class RateLimiterService {
    private final Map<UserTier, RateLimiter> rateLimiters = new HashMap<>();

    public RateLimiterService() {
        rateLimiters.put(UserTier.FREE,
                RateLimiterFactory.createRateLimiter(RateLimiterType.TOKEN_BUCKET,
                        new RateLimitConfig(10, 50)));

        rateLimiters.put(UserTier.PREMIUM,
                RateLimiterFactory.createRateLimiter(RateLimiterType.SLIDING_WINDOW_LOG,
                        new RateLimitConfig(100, 60)));
    }

    public boolean allowRequests(User user) {
        RateLimiter limiter = rateLimiters.get(user);
        if (limiter == null) {
            throw new IllegalArgumentException("No limiter configured for the user" + user.toString());
        }

        return limiter.allowRequests(user.getUserId());
    }
}
