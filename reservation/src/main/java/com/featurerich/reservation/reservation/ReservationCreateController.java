package com.featurerich.reservation.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ReservationCreateController {

    @GetMapping("/reservation/reservation-create")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('RESERVATION_RESERVATION_CREATE')")
    public ModelAndView getReservationCreate() {

        ModelAndView model = new ModelAndView("com/featurerich/blog/templates/reservation-create");
        model.addObject("reservationCreate", new ReservationCreate());

        return model;
    }

    @Getter
    @Setter
    private static class ReservationCreate {

        @NotBlank private Long unitId;

        @NotBlank private String name;

        @NotBlank private String description;

        @NotBlank private Long startAt;

        @NotBlank private Long endAt;
    }
}
