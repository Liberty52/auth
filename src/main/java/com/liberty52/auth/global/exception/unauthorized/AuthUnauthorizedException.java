package com.liberty52.auth.global.exception.unauthorized;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class AuthUnauthorizedException extends AbstractApiException {
    public AuthUnauthorizedException() {
        super(AuthErrorCode.AUTH_UNAUTHORIZED);
    }
}
