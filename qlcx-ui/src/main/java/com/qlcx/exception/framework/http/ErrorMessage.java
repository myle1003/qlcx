package com.qlcx.exception.framework.http;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
	private String message;
	
	private List<FieldErr> fieldErrs;
}
