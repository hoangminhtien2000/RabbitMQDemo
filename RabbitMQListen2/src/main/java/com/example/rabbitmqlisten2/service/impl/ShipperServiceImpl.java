package com.example.rabbitmqlisten2.service.impl;

import com.example.rabbitmqlisten2.constants.ResultConstants;
import com.example.rabbitmqlisten2.domain.dto.MessageDTO;
import com.example.rabbitmqlisten2.domain.dto.ResultDTO;
import com.example.rabbitmqlisten2.domain.entity.Shipper;
import com.example.rabbitmqlisten2.domain.errors.ExceptionConstants;
import com.example.rabbitmqlisten2.domain.errors.InternalServerException;
import com.example.rabbitmqlisten2.domain.request.ShipperRequest;
import com.example.rabbitmqlisten2.publisher.RabbitMQProducer;
import com.example.rabbitmqlisten2.repository.ShipperRepo;
import com.example.rabbitmqlisten2.service.ShipperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ShipperServiceImpl implements ShipperService {
    private final Logger log = LoggerFactory.getLogger(ShipperServiceImpl.class);
    private final String ENTITY_NAME = "SHIPPER";
    private final RabbitMQProducer producer;
    private final ShipperRepo shipperRepo;

    public ShipperServiceImpl(RabbitMQProducer producer, ShipperRepo shipperRepo) {
        this.producer = producer;
        this.shipperRepo = shipperRepo;
    }

    @Override
    public ResultDTO shipping(ShipperRequest request) {
        Optional<Shipper> shipper = shipperRepo.findById(request.getShipperId());
        if (shipper.isPresent()) {
            MessageDTO messageDTO = new MessageDTO(request);
            messageDTO.setReason(ResultConstants.SHIPPING);
            producer.sendMessage(messageDTO);
            return new ResultDTO(ResultConstants.SUCCESS, ResultConstants.SHIPPING, Boolean.TRUE);
        } else
        return new ResultDTO(ResultConstants.ERRORS, ExceptionConstants.SHIPPER_NOT_EXIST_VI, Boolean.FALSE);
    }

    @Override
    public ResultDTO shippingSuccess(ShipperRequest request) {
        Optional<Shipper> shipper = shipperRepo.findById(request.getShipperId());
        if (shipper.isPresent()) {
            MessageDTO messageDTO = new MessageDTO(request);
            messageDTO.setReason(ResultConstants.SHIPPING_SUCCESS);
            producer.sendMessage(messageDTO);
            return new ResultDTO(ResultConstants.SUCCESS, ResultConstants.SHIPPING_SUCCESS, Boolean.TRUE);
        } else
        return new ResultDTO(ResultConstants.ERRORS, ExceptionConstants.SHIPPER_NOT_EXIST_VI, Boolean.FALSE);
    }

    public void updateBill(ShipperRequest request) {
        Optional<Shipper> shipper = shipperRepo.findById(request.getShipperId());
        if (shipper.isPresent()) {
            MessageDTO messageDTO = new MessageDTO(request);
            producer.sendMessage(messageDTO);
        } else throw new InternalServerException(
                ExceptionConstants.SHIPPER_NOT_EXIST_VI,
                ENTITY_NAME,
                ExceptionConstants.SHIPPER_NOT_EXIST
        );
    }

}
