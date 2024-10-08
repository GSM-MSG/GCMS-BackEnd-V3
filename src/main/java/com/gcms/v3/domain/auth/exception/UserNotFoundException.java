package com.gcms.v3.domain.auth.exception;

import com.gcms.v3.global.error.BasicException;
import com.gcms.v3.global.error.ErrorCode;

public class UserNotFoundException extends BasicException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
