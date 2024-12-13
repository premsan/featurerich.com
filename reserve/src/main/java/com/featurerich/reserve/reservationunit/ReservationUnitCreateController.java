package com.featurerich.reserve.reservationunit;

import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ReservationUnitCreateController {

    @GetMapping("/reservation/reservation-unit-create")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('RESERVATION_RESERVATION_UNIT_CREATE')")
    public ModelAndView getReservationUnitCreate() {

        ModelAndView model =
                new ModelAndView("com/featurerich/blog/templates/reservation-unit-create");
        model.addObject("reservationUnitCreate", new ReservationUnitCreate());

        return model;
    }

    @Getter
    @Setter
    private static class ReservationUnitCreate {

        private TimeUnit timeUnit;

        private Long minUnit;

        private Long maxUnit;

        private Long startAt;

        private Long endAt;
    }
}
