package com.liberty52.auth.global.exception.internal;

import com.liberty52.auth.global.exception.AbstractApiException;

import static com.liberty52.auth.global.exception.AuthErrorCode.Auth_Not_Found;

public class AuthNotFoundException extends AbstractApiException {
    public AuthNotFoundException() {
        super(Auth_Not_Found);
    }
}
