package com.liberty52.auth.global.exception.internalservererror;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class InternalServerErrorException extends AbstractApiException {
    public InternalServerErrorException(String msg) {
        super(AuthErrorCode.INTERNAL_SERVER_ERROR, msg);
    }
}
