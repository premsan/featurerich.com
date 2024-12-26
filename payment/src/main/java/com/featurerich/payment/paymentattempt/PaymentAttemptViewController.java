package com.featurerich.payment.paymentattempt;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PaymentAttemptViewController {

    private final PaymentAttemptRepository paymentAttemptRepository;

    @GetMapping("/payment/payment-attempt-view/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('PAYMENT_PAYMENT_ATTEMPT_VIEW')")
    public ModelAndView getPaymentAttemptView(@PathVariable String id) {

        final Optional<PaymentAttempt> optionalPaymentAttempt =
                paymentAttemptRepository.findById(id);

        if (optionalPaymentAttempt.isEmpty()) {

            return new ModelAndView("com/featurerich/ui/templates/not-found");
        }

        final ModelAndView modelAndView =
                new ModelAndView("com/featurerich/payment/templates/payment-attempt-view");
        modelAndView.addObject("paymentAttempt", optionalPaymentAttempt.get());

        return modelAndView;
    }
}
