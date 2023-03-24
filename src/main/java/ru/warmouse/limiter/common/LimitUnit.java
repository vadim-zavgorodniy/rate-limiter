package ru.warmouse.limiter.common;

import java.util.concurrent.TimeUnit;

public enum LimitUnit {
    SECONDS,
    MINUTES,
    HOURS;

    public TimeUnit timeUnit() {
        TimeUnit unit;
        switch (this)  {
            case SECONDS:
                unit = TimeUnit.SECONDS;
                break;
            case MINUTES:
                unit = TimeUnit.MINUTES;
                break;
            case HOURS:
                unit = TimeUnit.HOURS;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
        return unit;
    }
}
