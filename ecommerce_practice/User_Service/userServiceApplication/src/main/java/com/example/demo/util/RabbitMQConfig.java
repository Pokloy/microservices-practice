package com.example.demo.util;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
    
    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange("payment.exchange");
    }
    
    @Bean
    public Queue paymentSuccessQueue() {
        return new Queue("payment.queue", true);
    }
    
    @Bean
    public Binding paymentSuccessBinding() {
        return BindingBuilder
                .bind(paymentSuccessQueue())
                .to(paymentExchange())
                .with("payment.success");
    }
    
    
//    @Bean
//    public Queue paymentSuccessQueue() {
//        return QueueBuilder.durable("paymentSuccessQueue")
//            .withArgument("x-dead-letter-exchange", "dlx.exchange")
//            .withArgument("x-dead-letter-routing-key", "dlx.routing.key")
//            .build();
//    }
//
//    
//    @Bean
//    public DirectExchange deadLetterExchange() {
//        return new DirectExchange("dlx.exchange");
//    }
//
//    @Bean
//    public Queue deadLetterQueue() {
//        return new Queue("dlx.queue");
//    }
//
//    @Bean
//    public Binding dlqBinding() {
//        return BindingBuilder
//            .bind(deadLetterQueue())
//            .to(deadLetterExchange())
//            .with("dlx.routing.key");
//    }

}
