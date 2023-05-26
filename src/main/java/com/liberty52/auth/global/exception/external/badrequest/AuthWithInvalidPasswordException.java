package com.liberty52.auth.global.exception.external.badrequest;

import com.liberty52.auth.global.exception.external.AbstractApiException;
import com.liberty52.auth.global.exception.external.AuthErrorCode;

public class AuthWithInvalidPasswordException extends AbstractApiException {

  public AuthWithInvalidPasswordException() {
    super(AuthErrorCode.INVALID_PASSWORD);
  }
}
