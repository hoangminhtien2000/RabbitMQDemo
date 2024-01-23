package com.example.rabbitmqlisten2.constants;

import java.util.List;

public interface BillConstants {
    interface Status {
        String SHIPPING = "SHIPPING";
        String SUCCESS = "SUCCESS";
        List<String> SHIPPING_SUCCESS = List.of(SHIPPING, SUCCESS);
    }
}
