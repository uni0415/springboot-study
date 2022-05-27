package com.springboot.todolist.web.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.todolist.domain.User;
import com.springboot.todolist.dto.SigninReqDto;
import com.springboot.todolist.dto.SignupReqDto;
import com.springboot.todolist.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/todo/signup")
	public ResponseEntity<?> signupToDo(@RequestBody SignupReqDto signupReqDto) {
		signupReqDto.setPassword(bCryptPasswordEncoder.encode(signupReqDto.getPassword()));
		if(authService.signup(signupReqDto)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/todo/signin")
	public ResponseEntity<?> signinToDo(SigninReqDto signinReqDto) {
		System.out.println(signinReqDto);
		User user = authService.signin(signinReqDto);
		System.out.println(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
