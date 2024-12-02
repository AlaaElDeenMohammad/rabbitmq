package com.alaaeldeen.rabbitmq.consumer;

import com.alaaeldeen.rabbitmq.consumer.config.MessagingConfig;
import com.alaaeldeen.rabbitmq.consumer.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message received from queue: "+orderStatus);
    }
}
