package com.liberty52.auth.global.exception.external.unauthorized;

import com.liberty52.auth.global.exception.external.AbstractApiException;
import com.liberty52.auth.global.exception.external.AuthErrorCode;

public class AuthUnauthorizedException extends AbstractApiException {
    public AuthUnauthorizedException() {
        super(AuthErrorCode.AUTH_UNAUTHORIZED);
    }
}
