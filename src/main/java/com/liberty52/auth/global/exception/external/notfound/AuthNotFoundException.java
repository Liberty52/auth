package com.liberty52.auth.global.exception.external.notfound;

import static com.liberty52.auth.global.exception.external.AuthErrorCode.AUTH_NOT_FOUND;

import com.liberty52.auth.global.exception.external.AbstractApiException;

public class AuthNotFoundException extends AbstractApiException {
    public AuthNotFoundException() {
        super(AUTH_NOT_FOUND);
    }
}
