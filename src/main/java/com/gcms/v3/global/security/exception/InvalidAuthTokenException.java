package com.gcms.v3.global.security.exception;

import com.gcms.v3.global.error.BasicException;
import com.gcms.v3.global.error.ErrorCode;

public class InvalidAuthTokenException extends BasicException {
    public InvalidAuthTokenException() {
        super(ErrorCode.INVALID_AUTH_TOKEN);
    }
}
