package com.example.rabbitmqlisten.consumer;

import com.example.rabbitmqlisten.domain.dto.BillDTO;
import com.example.rabbitmqlisten.domain.entity.Product;
import com.example.rabbitmqlisten.domain.dto.MessageDTO;
import com.example.rabbitmqlisten.domain.errors.ExceptionConstants;
import com.example.rabbitmqlisten.publisher.RabbitMQProducer;
import com.example.rabbitmqlisten.repository.ProductRepo;
import com.example.rabbitmqlisten.util.Converter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RabbitMQConsumer {

    private final String ENTITY_NAME = "PRODUCT";
    private final RabbitMQProducer producer;
    private final ProductRepo productRepo;

    public RabbitMQConsumer(RabbitMQProducer producer, ProductRepo productRepo) {
        this.producer = producer;
        this.productRepo = productRepo;
    }

    @RabbitListener(queues = "${rabbitmq.queue.tutorial}")
    public void onMessage(MessageDTO messageDTO) {
        System.out.println("Receiver from RabbitMQTutorial: " + messageDTO);
        BillDTO bill = Converter.toObject(messageDTO.getData(), BillDTO.class);
        if (messageDTO.isStatus() == Boolean.TRUE) {
            Optional<Product> product = productRepo.findById(bill.getProductId());
            if (product.isEmpty()) {
                messageDTO.setReason(ExceptionConstants.PRODUCT_NOT_EXIST_VI);
                producer.rollbackMessage(messageDTO);
            } else {
                int count = product.get().getQuantity() - bill.getQuantity();
                if (count >= 0) {
                    product.get().setQuantity(count);
                    productRepo.save(product.get());
                    MessageDTO send = new MessageDTO(bill);
                    producer.sendMessage(send);
                } else {
                    messageDTO.setReason(ExceptionConstants.PRODUCT_QUANTITY_EXCEEDED);
                    producer.rollbackMessage(messageDTO);
                }
            }
        }
    }
}
