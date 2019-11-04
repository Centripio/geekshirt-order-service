package com.geekshirt.orderservice.client;

import com.geekshirt.orderservice.config.OrderServiceConfig;
import com.geekshirt.orderservice.dto.Confirmation;
import com.geekshirt.orderservice.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentServiceClient {
    private RestTemplate restTemplate;

    @Autowired
    private OrderServiceConfig serviceConfig;

    public PaymentServiceClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public Confirmation authorize(PaymentRequest request) {
         Confirmation confirmation = restTemplate.postForObject(
                serviceConfig.getPaymentServiceUrl(), request, Confirmation.class);

        return confirmation;
    }
}
