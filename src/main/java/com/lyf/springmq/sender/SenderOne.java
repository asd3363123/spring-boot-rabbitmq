package com.lyf.springmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SenderOne {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg = "hello" + new Date();
        System.out.println("SenderOne:" + msg);
        rabbitTemplate.convertAndSend("helloQueue", msg);
    }

    public void sendDate() {
        Date date = new Date();
        System.out.println("SenderOne: Date " + date);
        rabbitTemplate.convertAndSend("helloQueue", date);
    }
}
