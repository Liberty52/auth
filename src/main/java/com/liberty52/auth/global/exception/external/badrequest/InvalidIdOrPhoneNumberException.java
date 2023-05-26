package com.liberty52.auth.global.exception.external.badrequest;

import com.liberty52.auth.global.exception.external.AbstractApiException;
import com.liberty52.auth.global.exception.external.AuthErrorCode;

public class InvalidIdOrPhoneNumberException extends AbstractApiException {

  public InvalidIdOrPhoneNumberException() {
    super(AuthErrorCode.INVALID_ID_OR_INVALID_PHONENUMBER);
  }
}
