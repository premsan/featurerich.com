package com.featurerich.payment.paymentgateway;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "com.featurerich.payment.gateway")
public class PaymentGatewayConfiguration {

    private Map<String, GatewayConfiguration> gatewayConfigurationById;

    @Getter
    @Setter
    private static class GatewayConfiguration {

        private String createPaymentUrl;
        private HttpMethod createPaymentMethod;

        private String fetchPaymentStatusUrl;
        private HttpMethod fetchPaymentStatusMethod;

        private String accountId;

        private String clientKeyId;

        private String clientKeySecret;

        private boolean minorUnit;
    }
}
