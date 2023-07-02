package com.qlcx.exception;

import javax.print.attribute.standard.Severity;

public class AuthenticationException extends BaseException {
  private static final long serialVersionUID = 1L;

  public AuthenticationException(String message, Throwable rootCause) {
    super(message, Severity.WARNING, rootCause);
  }
}
