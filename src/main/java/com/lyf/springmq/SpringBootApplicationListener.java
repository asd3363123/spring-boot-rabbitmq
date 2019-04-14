package com.lyf.springmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 为了RPC ，必须在RabbitTemplate里设置JSON转换器
 */
@Component
public class SpringBootApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        RabbitTemplate template = applicationReadyEvent.getApplicationContext().getBeanFactory().getBean(RabbitTemplate.class);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
    }
}
