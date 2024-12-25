package com.featurerich.payment.paymentrequest;

import jakarta.validation.Valid;
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
public class PaymentRequestCreateController {

    private final PaymentRequestRepository paymentRequestRepository;

    @GetMapping("/payment/payment-request-create")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('PAYMENT_PAYMENT_REQUEST_CREATE')")
    public ModelAndView getPaymentRequestCreate() {

        final ModelAndView model =
                new ModelAndView("com/featurerich/payment/templates/payment-request-create");

        model.addObject("paymentRequestCreate", new PaymentRequestCreate());

        return model;
    }

    @PostMapping("/payment/payment-request-create")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('PAYMENT_PAYMENT_REQUEST_CREATE')")
    public ModelAndView postPaymentCreate(
            @Valid @ModelAttribute("paymentRequestCreate")
                    final PaymentRequestCreate paymentRequestCreate,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes,
            @CurrentSecurityContext final SecurityContext securityContext) {

        final ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {

            modelAndView.setViewName("com/featurerich/payment/templates/payment-request-create");
            modelAndView.addObject("paymentRequestCreate", paymentRequestCreate);

            return modelAndView;
        }

        final PaymentRequest paymentRequest =
                paymentRequestRepository.save(
                        new PaymentRequest(
                                UUID.randomUUID().toString(),
                                null,
                                paymentRequestCreate.getPaymentId(),
                                paymentRequestCreate.getProcessorId(),
                                null,
                                System.currentTimeMillis(),
                                securityContext.getAuthentication().getName()));

        redirectAttributes.addAttribute("id", paymentRequest.getId());
        return new ModelAndView("redirect:/payment/payment-request-view/{id}");
    }

    @Getter
    @Setter
    private static class PaymentRequestCreate {

        private String paymentId;

        private String processorId;
    }
}
