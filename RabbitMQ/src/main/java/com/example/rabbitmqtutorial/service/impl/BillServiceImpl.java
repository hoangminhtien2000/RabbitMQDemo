package com.example.rabbitmqtutorial.service.impl;

import com.example.rabbitmqtutorial.constants.BillConstants;
import com.example.rabbitmqtutorial.constants.ResultConstants;
import com.example.rabbitmqtutorial.domain.dto.MessageDTO;
import com.example.rabbitmqtutorial.domain.dto.ResultDTO;
import com.example.rabbitmqtutorial.domain.entity.Bill;
import com.example.rabbitmqtutorial.domain.request.BillRequest;
import com.example.rabbitmqtutorial.publisher.RabbitMQProducer;
import com.example.rabbitmqtutorial.repository.BillRepo;
import com.example.rabbitmqtutorial.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

import java.util.Optional;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    private final Logger log = LoggerFactory.getLogger(BillServiceImpl.class);
    private final String ENTITY_NAME = "BILL";
    private final RabbitMQProducer producer;
    private final BillRepo billRepo;

    public BillServiceImpl(RabbitMQProducer producer, BillRepo billRepo) {
        this.producer = producer;
        this.billRepo = billRepo;
    }

    @Override
    public ResultDTO createBill(BillRequest request) {
        Bill bill = new Bill();
        BeanUtils.copyProperties(request, bill);
        bill.setStatus(BillConstants.Status.CREATING);
        Bill response = billRepo.save(bill);
        MessageDTO messageDTO = new MessageDTO(response);
        producer.sendMessage(messageDTO);
        return new ResultDTO(ResultConstants.SUCCESS, ResultConstants.BILL_SUCCESS, Boolean.TRUE);
    }

    @Override
    public ResultDTO update(BillRequest request) {
        Optional<Bill> response = billRepo.findById(request.getId());
        Bill bill = null;
        if (response.isPresent()) {
            //Sử dụng Deep Coppy để ngăn chặn tự động cập nhật "Dirty Checking" của Hibernate.
            bill = SerializationUtils.clone(response.get());
            if (request.getCode() != null) {
                bill.setCode(request.getCode());
            }
        }
        return new ResultDTO(ResultConstants.SUCCESS, ResultConstants.BILL_SUCCESS, Boolean.TRUE, bill);
    }
}
