package com.featurerich.reservation.scheduledjob;

import com.featurerich.reservation.reservation.ReservationRepository;
import com.featurerich.scheduled.ScheduledJob;

import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCleanUpOldReservationScheduledJob implements ScheduledJob {

    private enum Attribute {
        A
    }

    private final ReservationRepository reservationRepository;

    @Override
    public Class<ChronoUnit> attributeNames() {

        EnumMap<TimeUnit, String> ok = new EnumMap<>(TimeUnit.class);

        return ChronoUnit.class;
    }

    @Override
    public void run(final Map<String, String> attributes) {

        reservationRepository.deleteAll();
    }
}
