package com.liberty52.auth.global.exception.badrequest;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class BadRequestException extends AbstractApiException {
    public BadRequestException(String msg) {
        super(AuthErrorCode.BAD_REQUEST, msg);
    }
}
