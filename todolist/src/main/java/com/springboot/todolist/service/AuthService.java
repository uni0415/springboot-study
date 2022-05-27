package com.springboot.todolist.service;

import com.springboot.todolist.domain.User;
import com.springboot.todolist.dto.SigninReqDto;
import com.springboot.todolist.dto.SignupReqDto;

public interface AuthService {

	public boolean signup(SignupReqDto signupReqDto);
	
	public User signin(SigninReqDto signinReqDto);
}
