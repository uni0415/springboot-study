package com.springboot.todolist.service;

import com.springboot.todolist.domain.User;
import com.springboot.todolist.dto.SigninReqDto;
import com.springboot.todolist.dto.SignupReqDto;

public interface AuthService {

	public boolean signup(SignupReqDto signupReqDto);
	
	public User signin(SigninReqDto signinReqDto);
	public boolean checkUsername(String username);
	public String getNameByUsercode(int usercode);
	public int insertUser(User user);
}
