package com.liberty52.auth.global.exception.external;

public class InvalidIdOrPwException extends AbstractApiException{

  public InvalidIdOrPwException() {
    super(AuthErrorCode.INVALID_ID_OR_INVALID_PHONENUMBER);
  }
}
