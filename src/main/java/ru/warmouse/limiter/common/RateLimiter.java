package ru.warmouse.limiter.common;

import java.time.Clock;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private final long limit;
    private final TimeUnit unit;
    private final long window;
    private Clock clock = Clock.systemDefaultZone();
    private long currentLimit;
    private long startInterval = -1;

    private LimitViolationRegistrar limitViolationRegistrar;

    public RateLimiter(RateLimit rateLimit, LimitViolationRegistrar limitViolationRegistrar) {
        this.limit = rateLimit.getCount();
        this.unit = rateLimit.getTimeUnit();
        this.window = unit.toMillis(1);
        this.limitViolationRegistrar = limitViolationRegistrar;
    }

    public RateLimiter(RateLimit rateLimit) {
        this.limit = rateLimit.getCount();
        this.unit = rateLimit.getTimeUnit();
        this.window = unit.toMillis(1);
        refreshInterval();
    }

    public RateLimiter setClock(Clock clock) {
        this.clock = clock;
        return this;
    }

    public void setLimitViolationRegistrar(LimitViolationRegistrar limitViolationRegistrar) {
        this.limitViolationRegistrar = limitViolationRegistrar;
    }

    public synchronized boolean canProcess() {
        refreshInterval();
        return --currentLimit >= 0;
    }

    private void refreshInterval() {
        long currentTime = clock.millis();
        if ((currentTime - startInterval) > window) {
            if (currentLimit < 0 && limitViolationRegistrar != null) {
                limitViolationRegistrar.onLimitExceeded(LimitViolationEvent.builder()
                        .startPeriod(Instant.ofEpochMilli(startInterval))
                        .endPeriod(Instant.ofEpochMilli(startInterval + window))
                        .limit(limit)
                        .actual(-currentLimit + limit).build());
            }
            startInterval = currentTime;
            currentLimit = limit;
        }
    }
}
