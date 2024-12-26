package com.featurerich.payment.paymentgateway;

import com.featurerich.payment.paymentattempt.PaymentAttemptStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentAttemptStatusFetched {

    private PaymentAttemptStatus attemptStatus;
}
