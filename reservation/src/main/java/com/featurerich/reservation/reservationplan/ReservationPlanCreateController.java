package com.featurerich.reservation.reservationplan;

import jakarta.validation.Valid;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ReservationPlanCreateController {

    private final ReservationPlanRepository reservationPlanRepository;

    @GetMapping("/reservation/reservation-plan-create")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('RESERVATION_RESERVATION_PLAN_CREATE')")
    public ModelAndView getReservationPlanCreate() {

        ModelAndView model =
                new ModelAndView("com/featurerich/reservation/templates/reservation-plan-create");
        model.addObject("reservationPlanCreate", new ReservationPlanCreate());

        return model;
    }

    @PostMapping("/reservation/reservation-plan-create")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('RESERVATION_RESERVATION_PLAN_CREATE')")
    public ModelAndView postReservationPlanCreate(
            @Valid @ModelAttribute("reservationPlanCreate")
                    ReservationPlanCreate reservationPlanCreate,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @CurrentSecurityContext final SecurityContext securityContext) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {

            modelAndView.setViewName(
                    "com/featurerich/reservation/templates/reservation-plan-create");
            modelAndView.addObject("reservationPlanCreate", reservationPlanCreate);

            return modelAndView;
        }

        final ReservationPlan reservationPlan =
                reservationPlanRepository.save(
                        new ReservationPlan(
                                UUID.randomUUID().toString(),
                                null,
                                null,
                                reservationPlanCreate.getChronoUnit(),
                                reservationPlanCreate.getMinUnit(),
                                reservationPlanCreate.getMaxUnit(),
                                reservationPlanCreate.getStartAt(),
                                reservationPlanCreate.getEndAt(),
                                System.currentTimeMillis(),
                                securityContext.getAuthentication().getName()));

        redirectAttributes.addAttribute("id", reservationPlan.getId());
        return new ModelAndView("redirect:/reservation/reservation-plan-view/{id}");
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
