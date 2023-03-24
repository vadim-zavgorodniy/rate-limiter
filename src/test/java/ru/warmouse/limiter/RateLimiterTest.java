package ru.warmouse.limiter;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.warmouse.limiter.common.LimitUnit;
import ru.warmouse.limiter.common.RateLimit;
import ru.warmouse.limiter.common.RateLimiter;

class RateLimiterTest {
    @Test
    void canProcess_rateExceeded() throws InterruptedException {
        RateLimiter limiter = new RateLimiter(new RateLimit(10, LimitUnit.SECONDS));

        for (int i = 0; i < 20; ++i) {
            boolean result = limiter.canProcess();
            if (i < 10) {
                assertThat(result).isTrue();
            } else {
                assertThat(result).isFalse();
            }
        }
        Thread.sleep(1000);
        for (int i = 0; i < 20; ++i) {
            boolean result = limiter.canProcess();
            if (i < 10) {
                assertThat(result).isTrue();
            } else {
                assertThat(result).isFalse();
            }
        }
    }
}