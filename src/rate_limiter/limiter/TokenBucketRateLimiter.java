package rate_limiter.limiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import rate_limiter.enums.RateLimiterType;
import rate_limiter.model.RateLimitConfig;

public class TokenBucketRateLimiter extends RateLimiter {
    // need to have token bucket here that is thread safe
    private final Map<String, Integer> buckets = new ConcurrentHashMap<>();
    private final Map<String, Long> lastRefillTime = new HashMap<>();

    public TokenBucketRateLimiter(RateLimitConfig config) {
        super(RateLimiterType.TOKEN_BUCKET, config);
    }

    @Override
    public boolean allowRequests(String userId) {
        AtomicBoolean allowed = new AtomicBoolean();
        long now = System.currentTimeMillis() / 1000;

        buckets.compute(userId, (id, availableTokens) -> {
            int currentTokens = refillTokens(userId, now);

            if (currentTokens > 0) {
                allowed.set(true);
                return currentTokens - 1;
            } else {
                return currentTokens;
            }
        });

        return allowed.get();
    }

    // refill tokens in the bucket
    private int refillTokens(String userId, long now) {
        double refillRate = (double) config.getWindowInSeconds() / config.getMaxRequests();
        long lastRefill = lastRefillTime.getOrDefault(userId, now);
        long elapsedTime = (now - lastRefill) / 1000;
        int refillTokens = (int) (elapsedTime / refillRate);
        int currentTokens = buckets.getOrDefault(userId, config.getMaxRequests());
        currentTokens = Math.min(config.getMaxRequests(), currentTokens + refillTokens);
        if (refillTokens > 0) {
            lastRefillTime.put(userId, now);
        }

        return currentTokens;
    }
}
