package com.liberty52.auth.global.exception.internal;

import static com.liberty52.auth.global.exception.external.AuthErrorCode.INVALID_SOCIAL_LOGIN_CODE_ACCESSED;

import com.liberty52.auth.global.exception.external.AbstractApiException;

public class InvalidSocialLoginCodeAccessedException extends AbstractApiException {

    public InvalidSocialLoginCodeAccessedException(
            ) {
        super(INVALID_SOCIAL_LOGIN_CODE_ACCESSED);
    }
}
