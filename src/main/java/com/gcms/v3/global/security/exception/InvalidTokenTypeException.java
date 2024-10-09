package com.gcms.v3.global.security.exception;

import com.gcms.v3.global.error.BasicException;
import com.gcms.v3.global.error.ErrorCode;

public class InvalidTokenTypeException extends BasicException {
    public InvalidTokenTypeException() {
        super(ErrorCode.INVALID_TOKEN_TYPE);
    }
}
