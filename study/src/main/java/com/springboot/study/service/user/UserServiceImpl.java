package com.springboot.study.service.user;

import org.springframework.stereotype.Service;

import com.springboot.study.domain.user.UserRepository;
import com.springboot.study.web.dto.SignupReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	@Override
	public int signup(SignupReqDto signupReqDto) throws Exception {
		return userRepository.signup(signupReqDto.toEntity());
	}
	
}
