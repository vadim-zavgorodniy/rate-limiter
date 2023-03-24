package ru.warmouse.limiter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import ru.warmouse.limiter.common.LimitUnit;
import ru.warmouse.limiter.common.RateLimit;
import ru.warmouse.limiter.common.RateLimiter;
import ru.warmouse.limiter.service.RateLimiterService;

@SpringBootTest
public class RateLimitIT {
    @SpyBean
    private RateLimiterService rateLimiterService;

    public static final String TEST1_LIMITER_NAME = "test1";

    @Test
    void canProcess_rateExceeded() throws InterruptedException {
        for (int i = 0; i < 20; ++i) {
            boolean result = rateLimiterService.canProcess(TEST1_LIMITER_NAME);
            if (i < 10) {
                assertThat(result).isTrue();
            } else {
                assertThat(result).isFalse();
            }
        }
        Thread.sleep(1000);
        for (int i = 0; i < 20; ++i) {
            boolean result = rateLimiterService.canProcess(TEST1_LIMITER_NAME);
            if (i < 10) {
                assertThat(result).isTrue();
            } else {
                assertThat(result).isFalse();
            }
        }
    }
}
