package com.example.rabbitmqtutorial.domain.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private String reason;
    private Object data;
    private boolean status = false;

    public MessageDTO() {
    }

    public MessageDTO(String reason, Object data, boolean status) {
        this.reason = reason;
        this.data = data;
        this.status = status;
    }

    public MessageDTO(Object data, boolean status) {
        this.data = data;
        this.status = status;
    }

    public MessageDTO(Object data) {
        this.data = data;
    }

}
