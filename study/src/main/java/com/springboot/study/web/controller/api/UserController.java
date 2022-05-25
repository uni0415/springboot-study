package com.springboot.study.web.controller.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.study.config.auth.PrincipalDetails;
import com.springboot.study.config.auth.PrincipalDetailsService;
import com.springboot.study.domain.user.User;
import com.springboot.study.service.user.AccountService;
import com.springboot.study.web.dto.CMRespDto;
import com.springboot.study.web.dto.UpdateUserReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final AccountService accountService;
	private final PrincipalDetailsService principalDetailsService;

	@GetMapping("/{usercode}")
	public ResponseEntity<?> getUser(@PathVariable int usercode) {
		return new ResponseEntity<>(10, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/account/{username}")
	public ResponseEntity<?> updateUser(@PathVariable String username, @Valid UpdateUserReqDto updateUserReqDto,
			BindingResult bindingResult) {

		User user = new User();
		if (!user.getUsername().equals(username)) {
			return new ResponseEntity<>(new CMRespDto<String>(-1, "회원 조회 실패", username), HttpStatus.BAD_REQUEST);
		}
		user.setEmail(updateUserReqDto.getEmail());
		user.setName(updateUserReqDto.getName());
		return new ResponseEntity<>(new CMRespDto<User>(1, "업데이트 완료", user), HttpStatus.OK);
	}

	@DeleteMapping("/account/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		User user = new User();
		if (!user.getUsername().equals(username)) {
			return new ResponseEntity<>(new CMRespDto<String>(-1, "회원탈퇴 실패", username), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new CMRespDto<String>(1, "회원탈퇴 성공", username), HttpStatus.OK);
		}

	}

	@PutMapping("/account/profile/img")
	public ResponseEntity<?> updateProfileImg(@RequestPart MultipartFile file,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		file.getOriginalFilename();
		if (accountService.updateProfileImg(file, principalDetails)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	
	  @PutMapping("/account/profile")
	  public ResponseEntity<?> updateProfile(@RequestBody UpdateUserReqDto updateUserReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		  int usercode = principalDetails.getUser().getUsercode();
		  updateUserReqDto.setUsercode(usercode);
		  if(accountService.updateProfile(updateUserReqDto.toUpdateUserEntity())) {
			  User user = accountService.getUserByUsercode(usercode);
			  principalDetails.setUser(user);
			  return new ResponseEntity<>(HttpStatus.OK);
		  } else {
			  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		  }
	  }
	 

}
