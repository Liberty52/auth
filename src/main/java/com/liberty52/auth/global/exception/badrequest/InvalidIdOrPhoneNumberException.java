package com.liberty52.auth.global.exception.badrequest;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class InvalidIdOrPhoneNumberException extends AbstractApiException {

  public InvalidIdOrPhoneNumberException() {
    super(AuthErrorCode.INVALID_ID_OR_INVALID_PHONENUMBER);
  }
}
