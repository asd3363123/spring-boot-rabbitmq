package com.lyf.springmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SenderTwo {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg = "hello" + new Date();
        System.out.println("SenderTwo:" + msg);
        rabbitTemplate.convertAndSend("delayQueue", msg);
    }

    public void sendDate() {
        Date date = new Date();
        System.out.println("SenderTwo: Date " + date);
        rabbitTemplate.convertAndSend("mainExchange", "test.*", date);
    }

    public void sendDateWithRoutingKey(String routingKey) {
        System.out.println("SenderTwo: RoutingKey: " + routingKey);
        rabbitTemplate.convertAndSend("mainExchange", routingKey, routingKey);
    }
}
