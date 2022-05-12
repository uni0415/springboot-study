package com.springboot.study.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.handler.ex.CustomValidationApiExeption;
import com.springboot.study.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(CustomValidationApiExeption.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiExeption e) {
		return new ResponseEntity<>(new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap()),HttpStatus.BAD_REQUEST);
	}
}
