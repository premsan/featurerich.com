package com.featurerich.payment.paymentgateway;

public interface PaymentGateway {

    String id();

    PaymentAttemptCreated paymentAttemptCreate(final PaymentAttemptCreate paymentAttemptCreate);

    PaymentAttemptStatusFetched paymentAttemptStatusFetch(
            final PaymentAttemptStatusFetch paymentAttemptStatusFetch);
}
