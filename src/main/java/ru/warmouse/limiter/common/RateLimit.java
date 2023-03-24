package ru.warmouse.limiter.common;

import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RateLimit {
    long count;
    LimitUnit unit;

    public TimeUnit getTimeUnit() {
        return unit.timeUnit();
    }
}
