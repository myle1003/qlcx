package com.qlcx.exception.framework.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.qlcx.exception.AuthenticationException;
import com.qlcx.exception.BusinessException;
import com.qlcx.exception.ResourceNotFoundException;
import com.qlcx.exception.framework.http.ApiResult;
import com.qlcx.exception.framework.http.ResponseData;

import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseData> handleResourceNotFoundException(ResourceNotFoundException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResult.response(HttpStatus.NOT_FOUND, exception.getMsg(), null, null);
	}
	
	@ResponseBody
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ResponseData> handleAuthenticationException(AuthenticationException exception) {
		log.warn(exception.getMessage(), exception);
		return ApiResult.response(HttpStatus.UNAUTHORIZED, exception.getMsg(), null, null);
	}


	@ResponseBody
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResponseData> handleBusinessException(BusinessException exception) {
		log.warn(exception.getMessage(), exception);
		return ApiResult.response(HttpStatus.BAD_REQUEST, exception.getMsg(), null, null);
	}



	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseData> handleRuntimeException(RuntimeException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResult.response(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi không mong muốn đã xảy ra. Vui lòng liên hệ với admin.", null,
				null);
	}



}
