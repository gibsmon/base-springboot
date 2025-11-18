package com.poliymorf.dagaitem.util.exception;

import com.poliymorf.dagaitem.data.constanta.GlobalMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private String code;
    private String message;
    private String messageID;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(GlobalMessage globalMessage) {
        super(globalMessage.message);
        this.code = globalMessage.code;
        this.message = globalMessage.message;
    }

    public BusinessException(GlobalMessage globalMessage, String suffixAdditionalMessage) {
        super(globalMessage.message + " " + suffixAdditionalMessage);
        this.code = globalMessage.code;
        this.message = globalMessage.message + " " + suffixAdditionalMessage;
    }

    public BusinessException(String prefixAdditionalMessage, GlobalMessage globalMessage) {
        super(globalMessage.message + " " + prefixAdditionalMessage);
        this.code = globalMessage.code;
        this.message = prefixAdditionalMessage + " " + globalMessage.message;
    }

    public static BusinessException dataNotFound() {
        return new BusinessException(GlobalMessage.DATA_NOT_FOUND);
    }


    @SneakyThrows
    public static void throwError(GlobalMessage globalMessage, String additionalMessage) {
        throw new BusinessException(globalMessage, additionalMessage);
    }

    @SneakyThrows
    public static void throwError(GlobalMessage globalMessage) {
        throw new BusinessException(globalMessage);
    }

}

