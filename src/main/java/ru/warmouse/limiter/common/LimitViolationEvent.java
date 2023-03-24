package ru.warmouse.limiter.common;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

/**
 * Класс события нарушения лимита.
 * Событие генерируется при превышении разрешенного лимита по количеству запросов
 * к ресурсу в единицу времени.
 */
@Data
@Builder
public class LimitViolationEvent {
    /**
     * Начало периода регулирования.
     */
    private Instant startPeriod;
    /**
     * Окончание периода регулирования.
     */
    private Instant endPeriod;
    /**
     * Предел количества разрешенных.
     */
    private long limit;
    /**
     * Фактическое количество запросов.
     */
    private long actual;
}
