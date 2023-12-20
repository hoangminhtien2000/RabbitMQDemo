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
    public ResultDTO save(BillRequest request) {
        Bill bill = new Bill();
        BeanUtils.copyProperties(request, bill);
        bill.setStatus(BillConstants.Status.CREATING);
        Bill response = billRepo.save(bill);
        MessageDTO messageDTO = new MessageDTO(response);
        producer.sendMessage(messageDTO);
        return new ResultDTO(ResultConstants.SUCCESS, ResultConstants.BILL_SUCCESS, Boolean.TRUE);
    }
}
