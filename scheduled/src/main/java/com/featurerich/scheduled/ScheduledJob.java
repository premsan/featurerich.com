package com.featurerich.scheduled;

import java.time.Duration;
import java.util.Map;

public interface ScheduledJob {

    default String getId() {

        return getClass().getSimpleName();
    }

    default Duration delay() {

        return Duration.ofNanos(Long.MAX_VALUE);
    }

    default Class<? extends Enum<?>> attributeNames() {

        return null;
    }

    void run(final Map<String, String> attributes);
}
