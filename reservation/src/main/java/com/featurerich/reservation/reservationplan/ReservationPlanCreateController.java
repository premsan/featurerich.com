package com.featurerich.reservation.reservationplan;

import java.time.temporal.ChronoUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ReservationPlanCreateController {

    @GetMapping("/reservation/reservation-plan-create")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('RESERVATION_RESERVATION_PLAN_CREATE')")
    public ModelAndView getReservationPlanCreate() {

        ModelAndView model =
                new ModelAndView("com/featurerich/reservation/templates/reservation-plan-create");
        model.addObject("reservationPlanCreate", new ReservationPlanCreate());

        return model;
    }

    @Getter
    @Setter
    private static class ReservationPlanCreate {

        private Long resourceId;

        private ChronoUnit chronoUnit;

        private Long minUnit;

        private Long maxUnit;

        private Long startAt;

        private Long endAt;
    }
}
