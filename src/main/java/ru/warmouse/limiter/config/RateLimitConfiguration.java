package ru.warmouse.limiter.config;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.warmouse.limiter.common.RateLimit;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rate-limiter")
public class RateLimitConfiguration {
    private Map<String, RateLimit> limits = new HashMap<>();

    // @PostConstruct
    // public void init() {
    //     limits.put("test1", new RateLimit(10, TimeUnit.SECONDS));
    //     limits.put("test2", new RateLimit(100, TimeUnit.SECONDS));
    // }

}
