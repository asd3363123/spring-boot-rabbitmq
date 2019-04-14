package com.lyf.springmq.receiver;

import com.lyf.springmq.util.TestDTO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "deadQueue")
public class ReceiverRPC {

//    @RabbitHandler
//    public Object process(Object object) {
//        System.out.println("deadQueue : " + object);
//        return "skdjfksdjfk";
//    }

    @RabbitHandler
    public TestDTO process(Object object) {
        System.out.println("deadQueue : " + object);
        return new TestDTO("Test Success ");
    }


}
