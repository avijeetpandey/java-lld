# Rate limiter

A Rate limiter is a piece of software that decides the number of requests a user , device or an IP address can send to the server in a specified time window, if the threshold breaches, then it would probably send `HTTP-429` saying too many requests.

## Types of rate limiting algorithms

- **Fixed Window**
  - In fixed window algorithm, the numbers of requests allowed in a particular time interval only goes to the server , rest are denied, for example if only 6 requests are allowed per 24 hours, the 7th request within the 24 hour window will be rejected.
- **Sliding window log**
  - This algorithm solves edge bursting problem of the fixed window algorithm , wherein a user can send multiple request just at the edge of a window end and start, here what happens is , whenever the request comes in , it is being stored in a cache like redis and the system compares with the current timestamp, if the request are older than the current timestamp they are denied and rest is process , but however this is a memory intensive method.
- **Token Bucket**
  - In a token bucket algorithm , every request goes through a token of buckets and if the buckt does not contains any tokens the request is rejected , and the bucket gets filled as per a refill rate which is nothing but `time_window/number_of_requests`.

### Abstract class

An abstract class in java is defined using `abstract` keyword and is a class that cannot be initialised on its own , but it can have constructors or objects unlike interfaces , it is mostly used to give foundational pillars to specific classes sharing common functionalities.

```java
public abstract class RateLimiter {
  protected final RateLimitConfig config;
  protected final RateLimitType type;

  public RateLimiter(RateLimitConfig config, RateLimitType type) {
    this.config = config;
    this.type = type;
  }
}
```

