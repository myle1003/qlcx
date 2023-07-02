package com.qlcx.exception.framework.http;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult {


	public static ResponseEntity<ResponseData> response(HttpStatus httpStatus, String message, ResponseData data,
			List<FieldErr> fieldErrs) {
		if (data == null) {
			data = new ResponseData();
		}
		if (httpStatus.equals(HttpStatus.OK)) {
			data.setCode(1);
		} else {
			data.setCode(0);
			if (Objects.nonNull(message)) {
				data.setMsg(message);
			}

			
			data.setData(null);
		}
		return ResponseEntity.status(httpStatus).body(data);
	}
	
	public static ResponseEntity<ResponseData> failed() {
		return response(HttpStatus.BAD_REQUEST, "Thất bại", new ResponseData(), null);
	}

}
