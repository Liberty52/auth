package com.liberty52.auth.global.exception.external;

public class AuthWithInvalidPasswordException extends AbstractApiException{

  public AuthWithInvalidPasswordException() {
    super(AuthErrorCode.INVALID_PASSWORD);
  }
}
