package com.liberty52.auth.global.exception.notfound;

import static com.liberty52.auth.global.exception.AuthErrorCode.AUTH_NOT_FOUND;

import com.liberty52.auth.global.exception.AbstractApiException;

public class AuthNotFoundException extends AbstractApiException {
    public AuthNotFoundException() {
        super(AUTH_NOT_FOUND);
    }
}
