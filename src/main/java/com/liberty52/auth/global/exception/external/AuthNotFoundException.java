package com.liberty52.auth.global.exception.external;

import static com.liberty52.auth.global.exception.external.AuthErrorCode.AUTH_NOT_FOUND;

public class AuthNotFoundException extends AbstractApiException {
    public AuthNotFoundException() {
        super(AUTH_NOT_FOUND);
    }
}
