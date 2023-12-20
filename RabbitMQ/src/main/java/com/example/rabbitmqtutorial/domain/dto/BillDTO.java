package com.example.rabbitmqtutorial.domain.dto;

import lombok.Data;

@Data
public class BillDTO {
    private Integer id;
    private String code;
    private Integer productId;
    private Integer shipperId;
    private Integer quantity;
    private String status;
    private String address;
}
