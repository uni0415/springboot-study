package com.springboot.study.service.user;

import com.springboot.study.web.dto.SignupReqDto;

public interface UserService {
	public int signup(SignupReqDto signupReqDto) throws Exception;
}
