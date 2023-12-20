package com.example.rabbitmqlisten2.publisher;

import com.example.rabbitmqlisten2.domain.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER =  LoggerFactory.getLogger(RabbitMQProducer.class);

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MessageDTO messageDTO) {
        messageDTO.setStatus(true);
        LOGGER.info(String.format("Message send RabbitMQTutorial -> %s",messageDTO));
        rabbitTemplate.convertAndSend(exchange, routingKey, messageDTO);
    }

}
