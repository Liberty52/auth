package com.liberty52.auth.global.exception.internal;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;
import com.liberty52.auth.global.exception.ErrorCode;

public class AuthWithSuchEmailAlreadyExistsException extends AbstractApiException {
    public AuthWithSuchEmailAlreadyExistsException() {
        super(AuthErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
