package com.umg.serviciosInmobiliarios.dto;

import java.util.Date;

public class ErrorDto {
    private int status;
    private Date timestamp;
    private String message;
    private String detailMessage;

    public ErrorDto() {
    }

    public ErrorDto(int status, Date timestamp, String message, String detailMessage) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
