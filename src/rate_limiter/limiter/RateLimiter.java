package rate_limiter.limiter;

import rate_limiter.enums.RateLimiterType;
import rate_limiter.model.RateLimitConfig;

public abstract class RateLimiter {
    protected final RateLimiterType type;
    protected final RateLimitConfig config;

    public abstract boolean allowRequests(String userId);

    public RateLimiter(RateLimiterType type, RateLimitConfig config) {
        this.type = type;
        this.config = config;
    }
}
