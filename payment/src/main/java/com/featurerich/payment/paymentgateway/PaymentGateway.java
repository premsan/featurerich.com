package com.featurerich.payment.paymentgateway;

public interface PaymentGateway {

    String id();

    PaymentCreated paymentCreate(final PaymentCreate paymentCreate);

    PaymentStatusFetched paymentStatusFetch(final PaymentStatusFetch paymentStatusFetch);
}
