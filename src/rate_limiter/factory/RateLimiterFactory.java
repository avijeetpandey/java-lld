package rate_limiter.factory;

import rate_limiter.enums.RateLimiterType;
import rate_limiter.limiter.RateLimiter;
import rate_limiter.limiter.SlidingWindowRateLimiter;
import rate_limiter.limiter.TokenBucketRateLimiter;
import rate_limiter.model.RateLimitConfig;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimiterType type, RateLimitConfig config) {
        return switch (type) {
            case TOKEN_BUCKET -> new TokenBucketRateLimiter(config);
            case SLIDING_WINDOW_LOG -> new SlidingWindowRateLimiter(config);
            default -> throw new IllegalArgumentException("Unknown type" + type);
        };
    }
}
