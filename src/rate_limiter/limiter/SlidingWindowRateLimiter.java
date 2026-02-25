package rate_limiter.limiter;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import rate_limiter.enums.RateLimiterType;
import rate_limiter.model.RateLimitConfig;

public class SlidingWindowRateLimiter extends RateLimiter {
    private final Map<String, Queue<Long>> requestLog = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(RateLimitConfig config) {
        super(RateLimiterType.SLIDING_WINDOW_LOG, config);
    }

    @Override
    public boolean allowRequests(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis() / 1000;

        requestLog.compute(userId, (id, log) -> {
            if(log == null) {
                log = new ArrayDeque<>();
            }

            // sliding window thing
            if(!log.isEmpty() && (now - log.peek() <= config.getWindowInSeconds())) {
                log.poll();
            }

            if(log.size() < config.getMaxRequests()) {
                log.add(now);
                allowed.set(true);
            }

            return log;
        });

        return allowed.get();
    }
    
}
