package com.qlcx.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlcx.exception.framework.http.ApiResult;
import com.qlcx.exception.framework.http.ResponseData;

public class JwtAuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (HttpStatus.NOT_FOUND.value() == response.getStatus()) {
			setResponseError(response, HttpStatus.NOT_FOUND);
			System.out.println("sssssss");
			return false;
		}
		return true;
		
	}


	private void setResponseError(HttpServletResponse response, HttpStatus httpStatus) throws IOException {
		ResponseEntity<ResponseData> result = ApiResult.failed();
		switch (httpStatus) {
		case UNAUTHORIZED:
			result = ApiResult.response(httpStatus, "Lỗi đăng nhâp", null, null);
			break;
		case NOT_FOUND:
			result = ApiResult.response(httpStatus, "Không tìm thấy", null, null);
		default:
			break;
		}
		byte[] responseToSend = new ObjectMapper().writeValueAsString(result.getBody()).getBytes();
		response.setHeader("Content-type", "application/json");
		response.setStatus(httpStatus.value());
		response.getOutputStream().write(responseToSend);
	}
}
