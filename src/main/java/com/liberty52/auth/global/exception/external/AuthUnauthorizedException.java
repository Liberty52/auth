package com.liberty52.auth.global.exception.external;

public class AuthUnauthorizedException extends AbstractApiException {
    public AuthUnauthorizedException() {
        super(AuthErrorCode.AUTH_UNAUTHORIZED);
    }
}
