package com.lyf.springmq.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestConnection {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("test");
        factory.setPassword("123456");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            String callbackQueueName = channel.queueDeclare().getQueue();
            AMQP.BasicProperties props = new AMQP.BasicProperties().builder().replyTo(callbackQueueName).build();
            channel.basicPublish("", "rpc_queue", props, "message".getBytes());
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
