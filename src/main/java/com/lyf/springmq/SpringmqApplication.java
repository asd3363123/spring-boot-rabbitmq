package com.lyf.springmq;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.HashMap;

@SpringBootApplication

public class SpringmqApplication {

    /**
     * 是否为测试环境
     */
    private static boolean IS_TEST = true;

    /**
     * 普通队列，无任何设置(以下布尔值均为测试环境下的设置)
     */
    @Bean
    public Queue helloQueue() {
        return new Queue("helloQueue", !IS_TEST, IS_TEST, IS_TEST);
    }

    /**
     * 死信队列
     */
    @Bean
    public Queue deadQueue() {
        return new Queue("deadQueue", !IS_TEST, IS_TEST, IS_TEST);
    }

    /**
     * 延迟队列，消息过期时间为 10000 ms ,指定死信交换机
     */
    @Bean
    public Queue delayQueue() {
        return new Queue("delayQueue", !IS_TEST, IS_TEST, IS_TEST, new HashMap<String, Object>() {{
            put("x-message-ttl", 10000);
            put("x-dead-letter-exchange", "deadExchange");
        }});
    }

    /**
     * 优先级队列，最高优先级为10 。进入此队列的消息优先级默认为0 （最低）
     */
    @Bean
    public Queue priorityQueue() {
        return new Queue("priorityQueue", !IS_TEST, IS_TEST, IS_TEST, new HashMap<String, Object>() {{
            put("x-max-priority", 10);
        }});
    }

    /**
     * 死信交换器
     */
    @Bean
    public FanoutExchange deadLetterExchange() {
        return new FanoutExchange("deadExchange", !IS_TEST, IS_TEST);
    }

    /**
     * 主交换器
     */
    @Bean
    public TopicExchange mainExchange() {
        return new TopicExchange("mainExchange", !IS_TEST, IS_TEST);
    }

    /**
     * 绑定
     */
    @Bean
    public Binding deadLetterExchangeBinding(Queue deadQueue, FanoutExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange);
    }

    @Bean
    public Binding mainBinding1(Queue delayQueue, TopicExchange mainExchange) {
        return BindingBuilder.bind(delayQueue).to(mainExchange).with("test.delayQueue");
    }

    @Bean
    public Binding mainBinding2(Queue helloQueue, TopicExchange mainExchange) {
        return BindingBuilder.bind(helloQueue).to(mainExchange).with("test.#");
    }

    @Bean
    public Binding mainBinding3(Queue priorityQueue, TopicExchange mainExchange) {
        return BindingBuilder.bind(priorityQueue).to(mainExchange).with("test.priority");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringmqApplication.class, args);
    }

}
