package com.gcms.v3.domain.auth.exception;

import com.gcms.v3.global.error.BasicException;
import com.gcms.v3.global.error.ErrorCode;

public class ExpiredTokenException extends BasicException {
    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
