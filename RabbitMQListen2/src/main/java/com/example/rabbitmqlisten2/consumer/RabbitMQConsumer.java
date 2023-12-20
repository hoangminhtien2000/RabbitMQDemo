package com.example.rabbitmqlisten2.consumer;

import com.example.rabbitmqlisten2.publisher.RabbitMQProducer;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private final RabbitMQProducer rabbitMQProducer;

    public RabbitMQConsumer(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

//    @RabbitListener(queues = "${rabbitmq.queue.listen}")
//    public void onMessage(MessageDTO messageDTO) {
//        System.out.println("Receiver from RabbitMQListen: " + messageDTO);
//        Shipper customer = Converter.toObject(messageDTO.getData(), Shipper.class);
//        if (customer.getGender() != null && !customer.getGender().equals("male")) {
//                messageDTO.setReason("Gender không phải male");
//                rabbitMQProducer.rollbackMessage(messageDTO);
//            }
//        else {
//            messageDTO.setReason("Success");
//            rabbitMQProducer.sendMessage(messageDTO);
//        }
//    }
}
