package com.example.rabbitmqlisten2.domain.errors;

public class ExceptionConstants {
    public static final String SHIPPER_NOT_EXIST_VI = "Người giao hàng không tồn tại";
    public static final String SHIPPER_NOT_EXIST = "SHIPPER NOT EXIST";
    public static final String BILL_ID_NOT_NULL = "{\"code\": \"BILL_ID_NOT_NULL\",\"message\": \"Id bill không được bỏ trống\"}";
    public static final String SHIPPER_ID_NOT_NULL = "{\"code\": \"SHIPPER_ID_NOT_NULL\",\"message\": \"Id shipper không được bỏ trống\"}";
    public static final String BILL_STATUS_NOT_NULL = "{\"code\": \"BILL_STATUS_NOT_NULL\",\"message\": \"Trạng thái bill không được bỏ trống\"}";
    public static final String BILL_STATUS_INVALID_VI = "Trạng thái đơn hàng không hợp lệ";
}
