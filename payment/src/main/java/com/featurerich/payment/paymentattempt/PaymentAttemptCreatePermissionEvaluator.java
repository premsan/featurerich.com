package com.featurerich.payment.paymentattempt;

import com.featurerich.security.permissionevaluator.ObjectPermissionEvaluator;
import org.springframework.stereotype.Service;

@Service
public class PaymentAttemptCreatePermissionEvaluator
        implements ObjectPermissionEvaluator<PaymentAttemptCreateController.PaymentAttemptCreate> {

    @Override
    public Class<PaymentAttemptCreateController.PaymentAttemptCreate> evaluates() {

        return PaymentAttemptCreateController.PaymentAttemptCreate.class;
    }

    @Override
    public boolean hasPermission(
            String principalName,
            PaymentAttemptCreateController.PaymentAttemptCreate targetObject,
            String permission) {

        return false;
    }
}