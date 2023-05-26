package com.liberty52.auth.global.exception.external.badrequest;

import com.liberty52.auth.global.exception.external.AbstractApiException;
import com.liberty52.auth.global.exception.external.AuthErrorCode;

public class PageNumberOutOfRangeException extends AbstractApiException {

  public PageNumberOutOfRangeException() {
    super((AuthErrorCode.INVALID_PAGE_NUMBER));
  }
}
