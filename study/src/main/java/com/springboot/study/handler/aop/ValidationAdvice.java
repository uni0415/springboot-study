package com.springboot.study.handler.aop;
/*
 * Aspect: 여러 핵심 기능에 적용될 관심사 모듈을 의미한다. Aspect에는 구체적인 기능을 구현한 Advice와 Advice가 어디(Target)에서 적용될지 결정하는
 * 			PointCut을 포함하고 있다.
 * 
 * Target: 공통 기능을 부여할 대상. 즉 핵심 기능을 담당하는 비즈니스 로직이고, 어떤 관심사들과도 관계를 맺지 않는다.
 * 
 * Advice: 공통 기능을 담은 구현체. Advice는 Aspect가 무엇을 언제 적용할지를 정의하는 것.
 * 
 * PointCut: 공통 기능이 적용될 대상을 결정하는 것.
 * 
 * JoinPoint: Advice가 적용될 지점을 의미한다. Spring에서는 메서드에만 제공된다.
 * 
 */

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.springboot.study.handler.ex.CustomValidationApiExeption;

@Aspect
@Component
public class ValidationAdvice {
	public static Logger LOGGER = LoggerFactory.getLogger(ValidationAdvice.class);
	@Around("execution(* com.springboot.study.web.controller.api.*Controller.*(..))")
	public Object ApiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult)arg;
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<String, String>();
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					throw new CustomValidationApiExeption("유효성 검사 실패", errorMap);
				}
			}
		}
		return proceedingJoinPoint.proceed();
	}
}
