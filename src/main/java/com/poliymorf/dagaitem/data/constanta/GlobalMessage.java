package com.poliymorf.dagaitem.data.constanta;

public enum GlobalMessage {

    SUCCESS("00", "SUCCESS"),
    FAILED("01", "FAILED"),
    DATA_ALREADY_EXISTS("02", "Data Already Exists"),
    DATA_NOT_FOUND("03", "Data not found"),
    UNAUTHORIZED("04", "Unauthorized"),
    FAILED_PARSING("05", "Failed Parsing"),
    SUM_SAME("06", "Checksum Same"),

    ;

    public final String code;
    public final String message;

    GlobalMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
