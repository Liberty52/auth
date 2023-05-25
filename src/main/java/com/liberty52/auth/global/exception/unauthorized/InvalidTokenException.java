package com.liberty52.auth.global.exception.unauthorized;

import static com.liberty52.auth.global.exception.AuthErrorCode.INVALID_TOKEN;

import com.liberty52.auth.global.exception.AbstractApiException;

public class InvalidTokenException extends AbstractApiException {
  public InvalidTokenException() {
    super(INVALID_TOKEN);
  }
}
