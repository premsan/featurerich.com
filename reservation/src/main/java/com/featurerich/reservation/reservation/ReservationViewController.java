package com.featurerich.reservation.reservation;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ReservationViewController {

    private final ReservationRepository reservationRepository;

    @GetMapping("/reservation/reservation-view/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('RESERVATION_RESERVATION_VIEW')")
    public ModelAndView getReservationView(@PathVariable String id) {

        final Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if (optionalReservation.isEmpty()) {

            return new ModelAndView("com/featurerich/ui/templates/not-found");
        }

        final ModelAndView modelAndView =
                new ModelAndView("com/featurerich/reservation/templates/reservation-view");
        modelAndView.addObject("reservation", optionalReservation.get());

        return modelAndView;
    }
}
