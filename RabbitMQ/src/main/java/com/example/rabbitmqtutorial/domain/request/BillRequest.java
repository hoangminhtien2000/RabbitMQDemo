package com.example.rabbitmqtutorial.domain.request;

import lombok.Data;

@Data
public class BillRequest {
    private Integer id;
    private String code;
    private Integer productId;
    private Integer quantity;
    private String address;
}
