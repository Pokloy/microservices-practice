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

    /**
     * 1️⃣ Creates a Direct Exchange named 'order.exchange'.
     * 
     * ✔️ An exchange acts as a 'router' that directs messages to queues.
     * ✔️ A Direct Exchange routes messages to a queue based on the routing key.
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange("order.exchange");
    }

    /**
     * 2️⃣ Creates a Queue named 'order.queue'.
     * 
     * ✔️ A queue stores messages until they are consumed.
     * ✔️ This queue will receive messages that match the routing key.
     */
    @Bean
    public Queue orderQueue() {
        return new Queue("order.queue");
    }

    /**
     * 3️⃣ Binds the 'order.queue' to the 'order.exchange' using the routing key 'order.created'.
     * 
     * ✔️ Binding connects the queue to the exchange.
     * ✔️ Routing Key (order.created):
     *    - Only messages with this routing key will be delivered to this queue.
     * 
     * 🕹️ Flow:
     * Order-Service ----(order.created)----> order.exchange
     *                                |
     *                            [order.queue]
     *                                |
     *                        Payment-Service picks it up
     */
    @Bean
    public Binding orderBinding(Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder
                .bind(orderQueue)         // Bind order.queue
                .to(orderExchange)        // To order.exchange
                .with("order.created");   // With routing key order.created
    }

    /**
     * 4️⃣ Configures JSON message conversion.
     * 
     * ✔️ Converts Java objects to JSON before sending to RabbitMQ.
     * ✔️ Converts JSON back to Java objects when receiving.
     */
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 5️⃣ Configures RabbitTemplate to use the JSON converter.
     * 
     * ✔️ RabbitTemplate is used to send messages to the exchange.
     * ✔️ Sets the message converter to Jackson2JsonMessageConverter for JSON support.
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, 
                                         Jackson2JsonMessageConverter jsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
}
