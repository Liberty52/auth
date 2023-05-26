package com.liberty52.auth.global.exception.external.badrequest;

import com.liberty52.auth.global.exception.external.AbstractApiException;
import com.liberty52.auth.global.exception.external.AuthErrorCode;

public class PageSizeException extends AbstractApiException {

  public PageSizeException() {
    super((AuthErrorCode.INVALID_PAGE_SIZE));
  }
}
