package com.example.rabbitmqtutorial.controller;

import com.example.rabbitmqtutorial.domain.dto.ResultDTO;
import com.example.rabbitmqtutorial.domain.request.BillRequest;
import com.example.rabbitmqtutorial.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private final String ENTITY_NAME = "BILL";

    private final BillService billService;

    public CustomerResource( BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/rabbitmq-tutorial/create-bill")
    public ResponseEntity<ResultDTO> send(@RequestBody BillRequest request) {
        ResultDTO resultDTO = billService.save(request);
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

}
