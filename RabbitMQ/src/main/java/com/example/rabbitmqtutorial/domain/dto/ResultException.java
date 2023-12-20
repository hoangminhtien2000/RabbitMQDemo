package com.example.rabbitmqtutorial.domain.dto;

import lombok.Data;

@Data
public class ResultException {

    private String code;
    private String message;

    public ResultException() {}

    public ResultException(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
