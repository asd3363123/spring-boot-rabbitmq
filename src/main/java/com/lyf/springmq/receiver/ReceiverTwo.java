package com.lyf.springmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RabbitListener(queues = "helloQueue")
public class ReceiverTwo {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("ReceiverTwo: String " + msg);
    }

    @RabbitHandler
    public void process(Date date) {
        System.out.println("ReceiverTwo: Date " + date);
    }
}
