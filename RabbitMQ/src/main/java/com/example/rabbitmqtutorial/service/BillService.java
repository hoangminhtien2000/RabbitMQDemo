package com.example.rabbitmqtutorial.service;

import com.example.rabbitmqtutorial.domain.dto.ResultDTO;
import com.example.rabbitmqtutorial.domain.request.BillRequest;

public interface BillService {
    ResultDTO createBill(BillRequest request);
    ResultDTO update(BillRequest request);

}
