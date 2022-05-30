package com.springboot.todolist.service;

import org.springframework.stereotype.Service;

import com.springboot.todolist.domain.User;
import com.springboot.todolist.domain.UserRepository;
import com.springboot.todolist.dto.SigninReqDto;
import com.springboot.todolist.dto.SignupReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final UserRepository userRepository;

	@Override
	public boolean signup(SignupReqDto signupReqDto) {
		return userRepository.signup(signupReqDto.toUserEntity()) > 0;
	}
	
	@Override
	public User signin(SigninReqDto signinReqDto) {
		String password = userRepository.selectPassword(signinReqDto.getUsername());
		if(password.equals(signinReqDto.getPassword())) {
			return userRepository.loadUserByUsername(signinReqDto.getUsername());
		}
		return null;
	}
	
	@Override
	public boolean checkUsername(String username) {
		return userRepository.checkUsername(username)>0;
	}
	
	@Override
	public String getNameByUsercode(int usercode) {
		String name = userRepository.getNameByUsercode(usercode);
		return name;
	}
	
	@Override
	public int insertUser(User user) {
		return userRepository.insertUser(user);
	}
}
