package ru.warmouse.limiter.service;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.warmouse.limiter.common.LimitViolationEvent;
import ru.warmouse.limiter.common.LimitViolationRegistrar;
import ru.warmouse.limiter.common.RateLimit;
import ru.warmouse.limiter.common.RateLimiter;
import ru.warmouse.limiter.config.RateLimitConfiguration;

@Log4j2
@Service
@RequiredArgsConstructor
public class RateLimiterService {
    private Map<String, RateLimiter> limiters = new HashMap<>();

    private final RateLimitConfiguration rateLimitConfiguration;

    @PostConstruct
    public void init() {
        initServiceLimits(rateLimitConfiguration.getLimits());
    }

    private void initServiceLimits(Map<String, RateLimit> limits) {
        for (Map.Entry<String, RateLimit> limit : limits.entrySet()) {
            limiters.put(limit.getKey(), new RateLimiter(limit.getValue(), getViolationReporter(limit.getKey())));
            log.info(String.format("Registered rate limiter: %s with limit %d per %s",
                    limit.getKey(), limit.getValue().getCount(), limit.getValue().getUnit()));
        }
    }

    private LimitViolationRegistrar getViolationReporter(String resourceName) {
        return (LimitViolationEvent event) -> log.info(
                String.format("Rate limit exceeded. Resource: \"%s\". Period: [%s - %s]. Limit: %d. Actual: %d%n",
                        resourceName,
                        event.getStartPeriod(),
                        event.getEndPeriod(),
                        event.getLimit(),
                        event.getActual()));
    }

    public boolean canProcess(String resourceName) {
        RateLimiter rateLimiter = limiters.get(resourceName);
        if (rateLimiter == null) {
            return false;
        }
        return rateLimiter.canProcess();
    }
}
