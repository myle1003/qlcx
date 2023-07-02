package com.qlcx.exception;

import javax.print.attribute.standard.Severity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String msg;
  private Severity severity;
  @JsonIgnore
  private Throwable rootCause;

  public BaseException(String message, Severity severity, Throwable rootCause) {
    super(message, rootCause);
    this.msg = message;
    this.severity = severity;
    this.rootCause = rootCause;
  }

  public BaseException(String string, String code, Object[] args, Object object) {
    
  }
}
