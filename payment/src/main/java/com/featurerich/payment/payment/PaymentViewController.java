package com.featurerich.payment.payment;

import com.featurerich.application.FeatureMapping;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PaymentViewController {

    private final PaymentRepository paymentRepository;

    @FeatureMapping(module = "payment")
    @GetMapping("/payment/payment-view/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('PAYMENT_PAYMENT_VIEW')")
    public ModelAndView getPaymentView(@PathVariable String id) {

        final Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if (optionalPayment.isEmpty()) {

            return new ModelAndView("com/featurerich/ui/templates/not-found");
        }

        final ModelAndView modelAndView =
                new ModelAndView("com/featurerich/payment/templates/payment-view");
        modelAndView.addObject("payment", optionalPayment.get());

        return modelAndView;
    }
}
