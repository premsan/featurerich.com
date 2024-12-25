package com.featurerich.payment.paymentgateway;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentStatusFetch {

    private String paymentId;

    private String referenceId;

    private Map<String, String> gatewayAttributes;
}
