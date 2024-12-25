package com.featurerich.payment.paymentrequest;

import com.featurerich.payment.payment.Payment;
import com.featurerich.payment.payment.PaymentRepository;
import com.featurerich.payment.paymentgateway.PaymentCreate;
import com.featurerich.payment.paymentgateway.PaymentCreated;
import com.featurerich.payment.paymentgateway.PaymentGateway;
import com.featurerich.payment.paymentgateway.PaymentGatewayRepository;
import jakarta.validation.Valid;
import java.util.Currency;
import java.util.HashMap;
import java.util.Optional;
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

    private final PaymentRepository paymentRepository;
    private final PaymentRequestRepository paymentRequestRepository;
    private final PaymentGatewayRepository paymentGatewayRepository;

    @GetMapping("/payment/payment-request-create")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('PAYMENT_PAYMENT_REQUEST_CREATE')")
    public ModelAndView getPaymentRequestCreate() {

        final ModelAndView model =
                new ModelAndView("com/featurerich/payment/templates/payment-request-create");

        model.addObject(
                "gatewayIds", paymentGatewayRepository.findAll().stream().map(PaymentGateway::id));
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
            modelAndView.addObject(
                    "gatewayIds",
                    paymentGatewayRepository.findAll().stream().map(PaymentGateway::id));
            modelAndView.addObject("paymentRequestCreate", paymentRequestCreate);

            return modelAndView;
        }

        final PaymentGateway paymentGateway =
                paymentGatewayRepository.findById(paymentRequestCreate.getGatewayId());

        if (paymentGateway == null) {

            bindingResult.rejectValue("gatewayId", "Invalid gatewayId");

            modelAndView.setViewName("com/featurerich/payment/templates/payment-request-create");
            modelAndView.addObject(
                    "gatewayIds",
                    paymentGatewayRepository.findAll().stream().map(PaymentGateway::id));
            modelAndView.addObject("paymentRequestCreate", paymentRequestCreate);

            return modelAndView;
        }

        final Optional<Payment> optionalPayment =
                paymentRepository.findById(paymentRequestCreate.getPaymentId());

        if (optionalPayment.isEmpty()) {

            bindingResult.rejectValue("paymentId", "Invalid paymentId");

            modelAndView.setViewName("com/featurerich/payment/templates/payment-request-create");
            modelAndView.addObject(
                    "gatewayIds",
                    paymentGatewayRepository.findAll().stream().map(PaymentGateway::id));
            modelAndView.addObject("paymentRequestCreate", paymentRequestCreate);

            return modelAndView;
        }

        final String referenceId = UUID.randomUUID().toString();
        final PaymentCreate paymentCreate = new PaymentCreate();
        paymentCreate.setReferenceId(referenceId);
        paymentCreate.setAmount(optionalPayment.get().getAmount());
        paymentCreate.setCurrency(Currency.getInstance(optionalPayment.get().getCurrency()));

        final PaymentCreated paymentCreated = paymentGateway.paymentCreate(paymentCreate);

        final PaymentRequest paymentRequest =
                paymentRequestRepository.save(
                        new PaymentRequest(
                                referenceId,
                                null,
                                paymentRequestCreate.getPaymentId(),
                                paymentRequestCreate.getGatewayId(),
                                new HashMap<>(),
                                System.currentTimeMillis(),
                                securityContext.getAuthentication().getName()));

        return new ModelAndView("redirect:" + paymentCreated.getPaymentUrl());
    }

    @Getter
    @Setter
    private static class PaymentRequestCreate {

        private String paymentId;

        private String gatewayId;
    }
}
