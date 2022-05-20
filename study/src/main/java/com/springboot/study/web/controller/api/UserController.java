package com.springboot.study.web.controller.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.service.user.UserService;
import com.springboot.study.web.controller.api.data.User;
import com.springboot.study.web.dto.CMRespDto;
import com.springboot.study.web.dto.SigninReqDto;
import com.springboot.study.web.dto.SignupReqDto;
import com.springboot.study.web.dto.UpdateUserReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@GetMapping("/user/{usercode}")
	public ResponseEntity<?> getUser(@PathVariable int usercode) {
		System.out.println(usercode);
		return new ResponseEntity<>(10, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/auth/signup/check/{username}")
	public ResponseEntity<?> checkUsername(@PathVariable String username) {
		CMRespDto<String> cmRespDto = null;
		HttpStatus status = null;

		User user = new User();

		if (user.getUsername().equals(username)) {
			cmRespDto = new CMRespDto<String>(-1, "사용할 수 없는 사용자이름", username);
			status = HttpStatus.BAD_REQUEST;
		} else {
			cmRespDto = new CMRespDto<String>(-1, "사용할 수 있는 사용자이름", username);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(cmRespDto, status);
	}

	@PostMapping("/auth/signup")
	public ResponseEntity<?> signup(@Valid SignupReqDto signupReqDto, BindingResult bindingResult) throws Exception {
		return new ResponseEntity<>(new CMRespDto<SignupReqDto>(1, "회원가입 완료", signupReqDto), HttpStatus.OK);
	}

	@PostMapping("/auth/signin")
	public ResponseEntity<?> signin(@Valid SigninReqDto signinReqDto, BindingResult bindingResult) {
		User user = new User();
		
		if (user.getUsername().equals(signinReqDto.getUsername())
				&& user.getPassword().equals(signinReqDto.getPassword())) {
			return new ResponseEntity<>(new CMRespDto<User>(1, "로그인 성공", user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CMRespDto<SigninReqDto>(-1, "로그인 실패", signinReqDto),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/account/{username}")
	public ResponseEntity<?> updateUser(@PathVariable String username, @Valid UpdateUserReqDto updateUserReqDto, BindingResult bindingResult) {
		
		User user = new User();
		if(!user.getUsername().equals(username)) {
			return new ResponseEntity<>(new CMRespDto<String>(-1, "회원 조회 실패", username), HttpStatus.BAD_REQUEST);
		}
		user.setEmail(updateUserReqDto.getEmail());
		user.setName(updateUserReqDto.getName());
		return new ResponseEntity<>(new CMRespDto<User>(1, "업데이트 완료", user), HttpStatus.OK);
	}
	
	@DeleteMapping("/account/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		User user = new User();
		if(!user.getUsername().equals(username)) {
			return new ResponseEntity<>(new CMRespDto<String>(-1, "회원탈퇴 실패", username), HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(new CMRespDto<String>(1, "회원탈퇴 성공", username), HttpStatus.OK);
		}
		
	}
	
	
	
	
	
	
	
}
