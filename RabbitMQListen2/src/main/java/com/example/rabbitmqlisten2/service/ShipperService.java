package com.example.rabbitmqlisten2.service;

import com.example.rabbitmqlisten2.domain.dto.ResultDTO;
import com.example.rabbitmqlisten2.domain.request.ShipperRequest;

public interface ShipperService {
    ResultDTO shipping(ShipperRequest request);
}
