package com.geekshirt.orderservice.consumer;

import com.geekshirt.orderservice.dto.ShipmentOrderResponse;
import com.geekshirt.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShippingOrderConsumer {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "OUTBOUND_SHIPMENT_ORDER")
    public void receive(final ShipmentOrderResponse in) {
        log.debug(" [x] Received Shipment Information: {}'", in);
        orderService.updateShipmentOrder(in);
    }
}