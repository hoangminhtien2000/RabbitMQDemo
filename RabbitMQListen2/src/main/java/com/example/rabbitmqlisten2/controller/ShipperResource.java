package com.example.rabbitmqlisten2.controller;
import com.example.rabbitmqlisten2.domain.dto.ResultDTO;
import com.example.rabbitmqlisten2.domain.request.ShipperRequest;
import com.example.rabbitmqlisten2.service.ShipperService;
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
public class ShipperResource {

    private final Logger log = LoggerFactory.getLogger(ShipperResource.class);

    private final String ENTITY_NAME = "SHIPPER";

    private final ShipperService shipperService;

    public ShipperResource(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @PostMapping("/rabbitmq-listen2/shipping")
    public ResponseEntity<ResultDTO> shipping(@RequestBody ShipperRequest request) {
        ResultDTO resultDTO = shipperService.shipping(request);
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    @PostMapping("/rabbitmq-listen2/shipping-success")
    public ResponseEntity<ResultDTO> shippingSuccess(@RequestBody ShipperRequest request) {
        ResultDTO resultDTO = shipperService.shippingSuccess(request);
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }
}
