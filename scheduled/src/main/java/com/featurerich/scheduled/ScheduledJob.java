package com.featurerich.scheduled;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public interface ScheduledJob {

    default String getId() {
        return getClass().getSimpleName();
    }

    default Duration delay() {

        return Duration.ofNanos(Long.MAX_VALUE);
    }

    default Set<String> attributeNames() {

        return Collections.emptySet();
    }

    void run(final Map<String, String> attributes);
}
