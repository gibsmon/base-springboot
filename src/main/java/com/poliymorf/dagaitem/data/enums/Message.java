package com.poliymorf.dagaitem.data.enums;

public enum Message {

    SUCCESS("00", "SUCCESS"),
    FAILED("01", "FAILED");

    public final String code;
    public final String message;

    Message(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
