package com.example.rabbitmqlisten2.domain.request;

import com.example.rabbitmqlisten2.domain.errors.ExceptionConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ShipperRequest {
    @NotNull(message = ExceptionConstants.BILL_ID_NOT_NULL)
    private Integer id;
    @NotNull(message = ExceptionConstants.SHIPPER_ID_NOT_NULL)
    private Integer shipperId;
    @NotNull(message = ExceptionConstants.BILL_STATUS_NOT_NULL)
    private String status;
}
