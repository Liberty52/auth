package com.liberty52.auth.global.exception.badrequest;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class PageNumberOutOfRangeException extends AbstractApiException {

  public PageNumberOutOfRangeException() {
    super((AuthErrorCode.INVALID_PAGE_NUMBER));
  }
}
