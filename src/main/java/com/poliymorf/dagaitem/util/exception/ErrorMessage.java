package com.poliymorf.dagaitem.util.exception;

import java.util.Date;

public class ErrorMessage {
    private String code;
    private Date timestamp;
    private String message;
    private String description;



    public ErrorMessage(String code, Date timestamp, String message, String description) {
        this.code = code;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
