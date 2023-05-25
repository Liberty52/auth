package com.liberty52.auth.global.exception.notfound;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class NotFoundException extends AbstractApiException {
    public NotFoundException(String msg) {
        super(AuthErrorCode.NOT_FOUND, msg);
    }
}
