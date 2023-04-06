package com.liberty52.auth.global.exception.external;

public class InvalidIdOrPhoneNumberException extends AbstractApiException{

  public InvalidIdOrPhoneNumberException() {
    super(AuthErrorCode.INVALID_ID_OR_INVALID_PHONENUMBER);
  }
}
