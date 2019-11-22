package com.geekshirt.orderservice.controller.integration;

import com.geekshirt.orderservice.dto.OrderRequest;
import com.geekshirt.orderservice.dto.OrderResponse;
import com.geekshirt.orderservice.util.OrderServiceDataTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegration {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldCreateNewOrderWhenCreateOrderEndpointIsCalled() {
        OrderRequest orderRequest = OrderServiceDataTestUtils.getMockOrderRequest("1");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderRequest> entity = new HttpEntity<>(orderRequest, header);

        ResponseEntity<OrderResponse> response = testRestTemplate.postForEntity("/order/create", entity, OrderResponse.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        OrderResponse bodyResponse = response.getBody();
        Assertions.assertNotNull(bodyResponse);
        Assertions.assertEquals(orderRequest.getAccountId(), bodyResponse.getAccountId());
        Assertions.assertEquals(orderRequest.getItems().size(), bodyResponse.getDetails().size());
        Assertions.assertNotNull(bodyResponse.getOrderId());
        Assertions.assertFalse(bodyResponse.getOrderId().isEmpty());
        Assertions.assertEquals(Double.valueOf("1005d"), bodyResponse.getTotalAmount());
        Assertions.assertEquals(Double.valueOf("160.8"), bodyResponse.getTotalTax());
        Assertions.assertEquals(Double.valueOf("1165.8"), bodyResponse.getTotalAmountTax());
        Assertions.assertNotNull(bodyResponse.getTransactionDate());
    }

}
