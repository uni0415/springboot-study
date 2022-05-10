package com.springboot.study.web.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	//덧셈
	/*
	 * 요청 리소스 add(덧셈), sub(뺄셈), mul(곱셈), div(나눗셈)
	 * parameter = a, b
	 * 
	 * div 파라미터가 둘중 하나라도 0이면 0으로 계산 할 수 없습니다.
	 * 
	 */
	
	
	
	@GetMapping("/add")
	public String add(@RequestParam("v") List<Integer> values) {
		int result = 0;
		for(int i : values) {
			result += i;
		}
		return Integer.toString(result);
	}
	
	@GetMapping("/sub")
	public String sub(@RequestParam("v") List<Integer> values) {
		int result = 0;
		for(int i : values) {
			result -= i;
		}
		return Integer.toString(result);
	}
	
	@GetMapping("/mul")
	public String mul(@RequestParam("v") List<Integer> values) {
		int result = 1;
		for(int i : values) {
			result *= i;
		}
		return Integer.toString(result);
	}
	
	@GetMapping("/div")
	public String div(int a, int b) {
		if(a==0 || b==0) {
			return "0으로 계산 할 수 없습니다.";
		}
		return Integer.toString(a / b);
	}
}
