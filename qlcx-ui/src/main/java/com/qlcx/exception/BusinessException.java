package com.qlcx.exception;

import javax.print.attribute.standard.Severity;

public class BusinessException extends BaseException {
  private static final long serialVersionUID = 1L;

  public BusinessException(String message, Throwable rootCause) {
    super(message, Severity.WARNING, rootCause);
  }
}
