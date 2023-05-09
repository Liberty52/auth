package com.liberty52.auth.global.exception.external;

import static com.liberty52.auth.global.exception.external.AuthErrorCode.INVALID_TOKEN;

public class InvalidTokenException extends AbstractApiException {
  public InvalidTokenException() {
    super(INVALID_TOKEN);
  }
}
