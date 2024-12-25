package com.featurerich.payment.paymentgateway;

import java.math.BigDecimal;
import java.util.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCreate {

    private String referenceId;

    private BigDecimal amount;

    private Currency currency;
}
