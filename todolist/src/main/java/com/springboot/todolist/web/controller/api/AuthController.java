package com.springboot.todolist.web.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.todolist.annotation.app.Validation;
import com.springboot.todolist.auth.PrincipalDetails;
import com.springboot.todolist.domain.User;
import com.springboot.todolist.domain.UserRepository;
import com.springboot.todolist.dto.CMRespDto;
import com.springboot.todolist.dto.SigninReqDto;
import com.springboot.todolist.dto.SignupReqDto;
import com.springboot.todolist.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/*
	 * @PostMapping("/todo/signup") public ResponseEntity<?> signupToDo(@RequestBody
	 * SignupReqDto signupReqDto) {
	 * signupReqDto.setPassword(bCryptPasswordEncoder.encode(signupReqDto.
	 * getPassword())); if (authService.signup(signupReqDto)) { return new
	 * ResponseEntity<>(HttpStatus.OK); } else { return new
	 * ResponseEntity<>(HttpStatus.BAD_REQUEST); } }
	 */
	
	@PostMapping("/todo/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		List<String> roles = List.of("ROLE_USER");
		user.setRoles(String.join(",", roles));
		authService.insertUser(user);
		return new ResponseEntity<>(new CMRespDto<User>(1, "회원가입 완료", user), HttpStatus.OK);
	}

	@GetMapping("/todo/name")
	public ResponseEntity<?> getName(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		String name = "{\"name\":\"" + principalDetails.getName() + "\"}";
		return new ResponseEntity<>(name, HttpStatus.OK);
	}

	@PostMapping("/todo/signin")
	public ResponseEntity<?> signinToDo(@RequestBody SigninReqDto signinReqDto) {
		User user = authService.signin(signinReqDto);
		return new ResponseEntity<>(new CMRespDto<User>(1, "로그인 성공", user), HttpStatus.OK);
	}

	@PostMapping("/todo/checkUsername")
	public ResponseEntity<?> checkUsername(@RequestBody String username) {
		boolean result = authService.checkUsername(username);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> testUser() {
		return new ResponseEntity<>(new CMRespDto<String>(1, "유저권한", "role_user"), HttpStatus.OK);
	}
	
	@GetMapping("/admin")
	public ResponseEntity<?> testAdmin() {
		return new ResponseEntity<>(new CMRespDto<String>(1, "관리자권한", "role_admin"), HttpStatus.OK);
	}
}
