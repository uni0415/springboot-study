package com.springboot.study.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.springboot.study.handler.ex.CustomValidationApiExeption;

public class ValidationAop {

	private final Logger LOGGER = LoggerFactory.getLogger(ValidationAdvice.class);

	@Pointcut("within(* com.springboot.study.web.controller..*)")
	private void pointcut() {
	}

	@Pointcut("@annotation(com.springboot.study.annotation.Validation)")
	private void annotation() {
	}

	@Before("pointcut() && enableValid()")
	public void apiAdvice(JoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		LOGGER.info("유효성 검사중...");
		for (Object arg : args) {
			if (arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				if (bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<String, String>();
					for (FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					LOGGER.error("Validation AOP 실행됨");

					throw new CustomValidationApiExeption("유효성 검사 실패", errorMap);
				}
			}
		}
	}

	@AfterReturning(value = "pointcut() && enableValid()", returning = "returnObj")
	public void afterReturn(JoinPoint joinPoint, Object returnObj) {
		LOGGER.info("유효성 검사 완료: {}", returnObj);

	}
}
