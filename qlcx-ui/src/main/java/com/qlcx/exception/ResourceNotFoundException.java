package com.qlcx.exception;

import javax.print.attribute.standard.Severity;

public class ResourceNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message, Throwable rootCause) {
		super(message, Severity.ERROR, rootCause);
	}
}
