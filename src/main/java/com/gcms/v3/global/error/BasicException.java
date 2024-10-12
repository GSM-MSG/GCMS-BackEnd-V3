package com.gcms.v3.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BasicException extends RuntimeException{
    ErrorCode errorCode;
}
