package com.lyf.springmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "helloQueue")
public class ReceiverOne {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("ReceiverOne:" + msg);
    }
}
