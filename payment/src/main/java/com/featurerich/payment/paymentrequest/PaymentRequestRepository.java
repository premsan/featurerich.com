package com.featurerich.payment.paymentrequest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRequestRepository extends CrudRepository<PaymentRequest, String> {}
