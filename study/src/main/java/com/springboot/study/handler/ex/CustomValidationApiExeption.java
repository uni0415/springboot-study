package com.springboot.study.handler.ex;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class CustomValidationApiExeption extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errorMap = new HashMap<String, String>();
	 
	public CustomValidationApiExeption(String message) {
		super(message);
	}
	 
	public CustomValidationApiExeption(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}
}
