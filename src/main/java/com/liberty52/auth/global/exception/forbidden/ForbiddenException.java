package com.liberty52.auth.global.exception.forbidden;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class ForbiddenException extends AbstractApiException {
    public ForbiddenException(String msg) {
        super(AuthErrorCode.FORBIDDEN, msg);
    }
}
