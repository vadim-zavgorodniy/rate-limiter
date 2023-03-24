package ru.warmouse.limiter.common;

import java.time.Clock;
import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private final long limit;
    private final TimeUnit unit;
    private final long window;
    private Clock clock = Clock.systemDefaultZone();
    private long currentLimit;
    private long startInterval = -1;

    public RateLimiter(RateLimit rateLimit) {
        this.limit = rateLimit.getCount();
        this.unit = rateLimit.getTimeUnit();
        window = unit.toMillis(1);
        refreshInterval();
    }

    public synchronized boolean canProcess() {
        refreshInterval();
        return --currentLimit >= 0;
    }

    private void refreshInterval() {
        long currentTime = clock.millis();
        if ((currentTime - startInterval) > window) {
            startInterval = currentTime;
            if (currentLimit < 0) {
                System.out.printf("Rate limit exceeded. Limit set to %d. But actual was: %d%n", limit,
                        -currentLimit + limit);
            }
            currentLimit = limit;
        }
    }
}
