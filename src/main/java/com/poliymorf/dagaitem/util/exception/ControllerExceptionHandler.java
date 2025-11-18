package com.poliymorf.dagaitem.util.exception;

import com.poliymorf.dagaitem.data.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> globalExceptionHandler(BadCredentialsException e, WebRequest request) {
        return new ResponseEntity<>(BaseResponse.builder()
                .code(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .build(),
                HttpStatus.OK);
    }


    @ExceptionHandler(value = {BusinessException.class, HttpClientErrorException.Forbidden.class})
    public ResponseEntity<?> apiNotFound(BusinessException e) {
        return new ResponseEntity<>(BaseResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build(),
                HttpStatus.OK);
    }


}
