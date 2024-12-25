package com.featurerich.payment.paymentgateway;

import java.math.BigDecimal;
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
    private final PaymentGatewayConfiguration paymentGatewayConfiguration;

    @Override
    public String id() {

        return "adyen";
    }

    @Override
    public PaymentCreated paymentCreate(final PaymentCreate paymentCreate) {

        final PaymentGatewayConfiguration.Configuration configuration =
                paymentGatewayConfiguration.getGateway().get(id());

        final HashMap<String, Object> body = new HashMap<>();

        body.put("merchantAccount", configuration.getAccountId());

        final HashMap<String, Object> amount = new HashMap<>();
        amount.put(
                "value",
                paymentCreate
                        .getAmount()
                        .multiply(
                                BigDecimal.TEN.pow(
                                        paymentCreate.getCurrency().getDefaultFractionDigits())));
        amount.put("currency", paymentCreate.getCurrency().getCurrencyCode());
        body.put("amount", amount);

        body.put("returnUrl", "http://localhost");
        body.put("reference", paymentCreate.getReferenceId());
        body.put("mode", "hosted");
        body.put("themeId", "9cc783a4-76d4-450f-84e5-556f3e70ae10");

        final Map<String, Object> response =
                restClient
                        .method(HttpMethod.POST)
                        .uri(configuration.getCreatePaymentUrl())
                        .header("content-type", "application/json")
                        .header("x-API-key", configuration.getClientKeySecret())
                        .body(body)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});

        final PaymentCreated paymentCreated = new PaymentCreated();
        paymentCreated.setPaymentId((String) response.get("id"));
        paymentCreated.setPaymentUrl((String) response.get("url"));

        return paymentCreated;
    }

    @Override
    public PaymentStatusFetched paymentStatusFetch(final PaymentStatusFetch paymentStatusFetch) {

        final PaymentGatewayConfiguration.Configuration configuration =
                paymentGatewayConfiguration.getGateway().get(id());

        final Map<String, String> response =
                restClient
                        .method(HttpMethod.GET)
                        .uri(
                                UriComponentsBuilder.fromUriString(
                                                configuration.getFetchPaymentStatusUrl())
                                        .buildAndExpand("OK")
                                        .expand(
                                                new HashMap<>() {
                                                    {
                                                        put("sessionResult", "sessionResult");
                                                    }
                                                })
                                        .toUri())
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});

        final PaymentStatusFetched paymentStatusFetched = new PaymentStatusFetched();
        paymentStatusFetched.setPaymentStatus(map(response.get("status")));
        return paymentStatusFetched;
    }

    private PaymentStatus map(final String status) {

        switch (status) {
            case "completed":
                return PaymentStatus.COMPLETED;
            case "paymentPending":
                return PaymentStatus.PENDING;
            case "canceled":
                return PaymentStatus.CANCELLED;
            case "expired":
                return PaymentStatus.EXPIRED;
            default:
                return null;
        }
    }
}
