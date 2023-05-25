package com.liberty52.auth.global.exception.badrequest;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class AuthWithInvalidPasswordException extends AbstractApiException {

  public AuthWithInvalidPasswordException() {
    super(AuthErrorCode.INVALID_PASSWORD);
  }
}
