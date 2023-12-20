package com.example.rabbitmqlisten.config;

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

    @Value("${rabbitmq.queue.tutorial}")
    String queueTutorial;

    @Value("${rabbitmq.exchange.tutorial}")
    String exchangeTutorial;

    @Value("${rabbitmq.routing.key.tutorial}")
    private String routingKeyTutorial;

    @Bean
    Queue queueTutorial() {
        return new Queue(queueTutorial, false);
    }

    @Bean
    DirectExchange exchangeTutorial() {
        return new DirectExchange(exchangeTutorial);
    }

    @Bean
    Binding bindingTutorial() {
        return BindingBuilder.bind(queueTutorial()).to(exchangeTutorial()).with(routingKeyTutorial);
    }

    @Value("${rabbitmq.queue.rollback}")
    String queueRollback;

    @Value("${rabbitmq.exchange.rollback}")
    String exchangeRollBack;

    @Value("${rabbitmq.routing.key.rollback}")
    private String routingKeyRollBack;

    @Bean
    Queue queueRollback() {
        return new Queue(queueRollback, false);
    }

    @Bean
    DirectExchange exchangeRollBack() {
        return new DirectExchange(exchangeRollBack);
    }

    @Bean
    Binding bindingRollBack() {
        return BindingBuilder.bind(queueRollback()).to(exchangeRollBack()).with(routingKeyRollBack);
    }

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

}
