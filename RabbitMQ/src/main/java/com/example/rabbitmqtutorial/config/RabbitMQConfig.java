package com.example.rabbitmqtutorial.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
//Tạo queue và Binding
    @Value("${rabbitmq.queue.name}")
    String queue;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Bean
    Queue queue() {
        return new Queue(queue, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Value("${rabbitmq.queue.rollback}")
    String queueRollback;

    @Value("${rabbitmq.exchange.rollback}")
    String exchangeRollback;

    @Value("${rabbitmq.routing.key.rollback}")
    private String routingKeyRollback;

    @Bean
    Queue queueRollback() {
        return new Queue(queueRollback, false);
    }

    @Bean
    DirectExchange exchangeRollback() {
        return new DirectExchange(exchangeRollback);
    }

    @Bean
    Binding bindingRollBack() {
        return BindingBuilder.bind(queueRollback()).to(exchangeRollback()).with(routingKeyRollback);
    }

    @Value("${rabbitmq.queue.listen}")
    String queueListen;

    @Value("${rabbitmq.exchange.listen}")
    String exchangeListen;

    @Value("${rabbitmq.routing.key.listen}")
    private String routingKeyListen;

    @Bean
    Queue queueListen() {
        return new Queue(queueListen, false);
    }

    @Bean
    DirectExchange exchangeListen() {
        return new DirectExchange(exchangeListen);
    }

    @Bean
    Binding bindingListen() {
        return BindingBuilder.bind(queueListen()).to(exchangeListen()).with(routingKeyListen);
    }

    @Value("${rabbitmq.queue.listen2}")
    String queueListen2;

    @Value("${rabbitmq.exchange.listen2}")
    String exchangeListen2;

    @Value("${rabbitmq.routing.key.listen2}")
    private String routingKeyListen2;

    @Bean
    Queue queueListen2() {
        return new Queue(queueListen2, false);
    }

    @Bean
    DirectExchange exchangeListen2() {
        return new DirectExchange(exchangeListen2);
    }

    @Bean
    Binding bindingListen2() {
        return BindingBuilder.bind(queueListen2()).to(exchangeListen2()).with(routingKeyListen2);
    }

}
