package com.example.rabbitmqlisten.publisher;

import com.example.rabbitmqlisten.constants.MessageConstants;
import com.example.rabbitmqlisten.domain.dto.MessageDTO;
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

    @Value("${rabbitmq.exchange.rollback}")
    private String exchangeRollback;

    @Value("${rabbitmq.routing.key.rollback}")
    private String routingKeyRollback;

    private final RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER =  LoggerFactory.getLogger(RabbitMQProducer.class);

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MessageDTO messageDTO) {
        messageDTO.setStatus(Boolean.TRUE);
        messageDTO.setReason(MessageConstants.Status.SUCCESS);
        LOGGER.info(String.format("Message send RabbitMQTutorial -> %s",messageDTO));
        rabbitTemplate.convertAndSend(exchange, routingKey, messageDTO);
    }

    public void rollbackMessage(MessageDTO messageDTO) {
        messageDTO.setStatus(Boolean.FALSE);
        LOGGER.info(String.format("Message rollback RabbitMQTutorial -> %s",messageDTO));
        rabbitTemplate.convertAndSend(exchangeRollback, routingKeyRollback, messageDTO);

    }
}
