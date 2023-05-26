package com.liberty52.auth.global.exception.external.unauthorized;

import static com.liberty52.auth.global.exception.external.AuthErrorCode.INVALID_TOKEN;

import com.liberty52.auth.global.exception.external.AbstractApiException;

public class InvalidTokenException extends AbstractApiException {
  public InvalidTokenException() {
    super(INVALID_TOKEN);
  }
}
