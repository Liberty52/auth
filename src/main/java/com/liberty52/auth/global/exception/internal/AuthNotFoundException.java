package com.liberty52.auth.global.exception.internal;

import com.liberty52.auth.global.exception.AbstractApiException;

import static com.liberty52.auth.global.exception.AuthErrorCode.AUTH_NOT_FOUND;

public class AuthNotFoundException extends AbstractApiException {
    public AuthNotFoundException() {
        super(AUTH_NOT_FOUND);
    }
}
