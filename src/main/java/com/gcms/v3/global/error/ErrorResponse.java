package com.gcms.v3.global.error;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
public record ErrorResponse(
        HttpStatus status,
        String message
) {
    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode e){
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(e.getHttpStatus())
                        .message(e.getMessage())
                        .build()
                );
    }
}
