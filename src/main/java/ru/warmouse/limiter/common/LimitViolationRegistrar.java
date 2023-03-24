package ru.warmouse.limiter.common;

@FunctionalInterface
public interface LimitViolationRegistrar {
    /**
     * Метод обратной связи для регистрации нарушения лимита по количеству запросов в единицу времени.
     * @param violationEvent событие с данными о нарушении
     */
    void onLimitExceeded(LimitViolationEvent violationEvent);
}
