package com.lyf.springmq;

import com.lyf.springmq.sender.SenderOne;
import com.lyf.springmq.sender.SenderTwo;
import com.lyf.springmq.util.TestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringmqApplicationTests {

    @Autowired
    private SenderOne senderOne;

    @Autowired
    private SenderTwo senderTwo;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void send() {
        for (int i = 0; i < 4; i++) {
            senderOne.send();
            senderOne.sendDate();
        }
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        System.out.println("a:" + a);
    }

    @Test
    public void sendTwo() {
        senderTwo.send();
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        System.out.println("a:" + a);
    }

    @Test
    public void sendTwoDate() {
        senderTwo.sendDate();
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        System.out.println("a:" + a);
    }

    @Test
    public void testRoutingKey() {
//        senderTwo.sendDateWithRoutingKey("test.*");
//        senderTwo.sendDateWithRoutingKey("test.*elloQueue");
        senderTwo.sendDateWithRoutingKey("test.dfsdf");
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        System.out.println("a:" + a);
    }

    @Test
    public void testAmqpTemplate() {
//        amqpTemplate.convertAndSend("jdshfjdshfjhdf");
//        Object obj = amqpTemplate.convertSendAndReceive("deadQueue", "dfjsdhf");
        TestDTO obj = amqpTemplate.convertSendAndReceiveAsType("deadQueue", "dfjsdhf", new ParameterizedTypeReference<TestDTO>() {
        });
        System.out.println(obj);
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        System.out.println("a:" + a);
    }


}
