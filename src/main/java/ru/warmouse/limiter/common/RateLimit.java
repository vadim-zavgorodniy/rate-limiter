package ru.warmouse.limiter.common;

import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RateLimit {
    /**
     * Максимальное количество обращений к ресурсу.
     */
    long count;
    /**
     * Период времени в рамках которого регулируется количество обращений.
     */
    LimitUnit unit;

    /**
     * Возвращает TimeUnit соответствующий периоду unit.
     * @return TimeUnit соответствующий периоду unit
     */
    public TimeUnit getTimeUnit() {
        return unit.timeUnit();
    }
}
