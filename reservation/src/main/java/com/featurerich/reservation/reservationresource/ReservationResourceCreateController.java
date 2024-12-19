package com.featurerich.reservation.reservationresource;

import com.featurerich.reservation.scheduledjob.ReservationCleanUpOldReservationScheduledJob;
import com.featurerich.scheduled.ScheduledJob;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ReservationResourceCreateController {

    private final List<ScheduledJob> job;
    private final ReservationResourceRepository reservationResourceRepository;

    @GetMapping("/reservation/reservation-resource-create")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('RESERVATION_RESERVATION_RESOURCE_CREATE')")
    public ModelAndView getReservationResourceCreate() {


        ReservationResourceCreate reservationResourceCreate = new ReservationResourceCreate();
//        EnumMap<ChronoUnit, String> x = new EnumMap(job.get(0).attributeNames());
//
//        reservationResourceCreate.setAttributes(x);

        final ModelAndView model =
                new ModelAndView(
                        "com/featurerich/reservation/templates/reservation-resource-create");
        model.addObject("enumClass", job.get(0).attributeNames());
        model.addObject("reservationResourceCreate", reservationResourceCreate);

        return model;
    }

    @PostMapping("/reservation/reservation-resource-create")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('RESERVATION_RESERVATION_RESOURCE_CREATE')")
    public ModelAndView postReservationResourceCreate(
            @Valid @ModelAttribute("reservationResourceCreate")
                    final ReservationResourceCreate reservationResourceCreate,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes,
            @CurrentSecurityContext final SecurityContext securityContext) {

        final ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {

            modelAndView.setViewName(
                    "com/featurerich/reservation/templates/reservation-resource-create");
            modelAndView.addObject("enumClass", job.get(0).attributeNames());

            modelAndView.addObject("reservationResourceCreate", reservationResourceCreate);

            return modelAndView;
        }

        final ReservationResource reservationResource =
                reservationResourceRepository.save(
                        new ReservationResource(
                                UUID.randomUUID().toString(),
                                null,
                                reservationResourceCreate.getName(),
                                reservationResourceCreate.getDescription(),
                                System.currentTimeMillis(),
                                securityContext.getAuthentication().getName(), reservationResourceCreate.getAttributes()));

        redirectAttributes.addAttribute("id", reservationResource.getId());
        return new ModelAndView("redirect:/reservation/reservation-resource-view/{id}");
    }

    @Getter
    @Setter
    private static class ReservationResourceCreate {

        @NotBlank private String name;

        @NotBlank private String description;

        private Map<String, String> attributes;
    }

    private static interface Attr {

    }
    private static enum AttrEnum implements PredefinedMap.Key {

        A,
        B,
        C,
        D,
        E;
    }
}
