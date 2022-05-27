package com.springboot.todolist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/index")
	public String index() {
		return "todolist/todolist";
	}
	
	@GetMapping("/signin")
	public String signin() {
		return "todolist/signin";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "todolist/signup";
	}
}
