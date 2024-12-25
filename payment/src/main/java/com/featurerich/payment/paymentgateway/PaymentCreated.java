package com.featurerich.payment.paymentgateway;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCreated {

    private String paymentId;

    private String paymentUrl;
}
