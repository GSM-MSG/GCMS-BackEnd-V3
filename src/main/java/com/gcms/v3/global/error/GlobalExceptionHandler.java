package com.gcms.v3.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BasicException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(BasicException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
