package com.example.rabbitmqlisten.domain.dto;

import java.util.List;

/**
 * @author hieug
 */
public class ResultDTO {

    private Object message;
    private String reason;
    private boolean status = false;
    private Object data;
    private Integer count;

    public ResultDTO() {}

    public ResultDTO(Object message, String reason) {
        if (message instanceof String) {
            message = List.of(new ResultException(message.toString(), reason));
        }
        this.message = message;
        this.reason = reason;
    }

    public ResultDTO(Object message, String reason, boolean status) {
        this.message = message;
        this.reason = reason;
        this.status = status;
    }

    public ResultDTO(Object message, String reason, boolean status, Object data) {
        this.message = message;
        this.reason = reason;
        this.status = status;
        this.data = data;
    }

    public ResultDTO(Object message, String reason, boolean status, Object data, Integer count) {
        this.message = message;
        this.reason = reason;
        this.status = status;
        this.data = data;
        this.count = count;
    }

    public Object getMessage() {
        if (message instanceof String) {
            message = List.of(new ResultException(message.toString(), reason));
        }
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
