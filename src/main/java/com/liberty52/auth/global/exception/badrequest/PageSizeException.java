package com.liberty52.auth.global.exception.badrequest;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class PageSizeException extends AbstractApiException {

  public PageSizeException() {
    super((AuthErrorCode.INVALID_PAGE_SIZE));
  }
}
