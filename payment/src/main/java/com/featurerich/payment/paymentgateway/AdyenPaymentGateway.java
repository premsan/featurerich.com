package com.featurerich.payment.paymentgateway;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class AdyenPaymentGateway implements PaymentGateway {

    private final RestClient restClient;

    @Override
    public String id() {

        return "ADYEN";
    }

    @Override
    public PaymentCreated paymentCreate(final PaymentCreate paymentCreate) {

        final HashMap<String, Object> body = new HashMap<>();
        body.put("merchantAccount", "merchantAccount");

        final HashMap<String, Object> amount = new HashMap<>();
        amount.put("value", 1000);
        amount.put("currency", "EUR");

        body.put("amount", amount);

        body.put("returnUrl", "http://localhost");
        body.put("reference", "YOUR_PAYMENT_REFERENCE");
        body.put("mode", "hosted");
        body.put("themeId", "AZ1234567");

        final Map<String, Object> response =
                restClient
                        .method(HttpMethod.GET)
                        .uri("https://checkout-test.adyen.com/v70/sessions")
                        .header("content-type", "application/json")
                        .header("x-API-key", "ADYEN_API_KEY")
                        .body(body)
                        .retrieve()
                        .body(
                                new ParameterizedTypeReference<>() {
                                    @Override
                                    public Type getType() {
                                        return super.getType();
                                    }
                                });

        return null;
    }

    @Override
    public PaymentStatusFetched paymentStatusFetch(final PaymentStatusFetch paymentStatusFetch) {

        restClient
                .method(HttpMethod.GET)
                .uri(
                        UriComponentsBuilder.fromUriString(
                                        "https://checkout-test.adyen.com/v70/sessions/{0}")
                                .buildAndExpand("OK")
                                .expand(
                                        new HashMap<>() {
                                            {
                                                put("sessionResult", "sessionResult");
                                            }
                                        })
                                .toUri());

        return null;
    }
}
