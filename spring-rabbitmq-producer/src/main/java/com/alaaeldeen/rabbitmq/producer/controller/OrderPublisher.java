package com.alaaeldeen.rabbitmq.producer.controller;

import com.alaaeldeen.rabbitmq.producer.config.MessagingConfig;
import com.alaaeldeen.rabbitmq.producer.dto.Order;
import com.alaaeldeen.rabbitmq.producer.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        //restaurant service
        //payment service
        OrderStatus orderStatus = new OrderStatus(order,"PROCESS", "Order was placed successfully in "+restaurantName);
        template.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.ROUTING_KEY,orderStatus);
        return "Success !!!";
    }
}
