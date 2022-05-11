package com.springboot.study.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupReqDto {
	@NotBlank(message = "빈값일 수 없습니다.")
	@Email(message = "이메일 형식을 확인해 주세요")
	private String email;
	@NotBlank
	private String name;
	@NotBlank
	private String username;
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W).{8,20}",
			message = "비밀번호는 영문 대소문자와 숫자, 특수기호가 적어도 하나 이상 포함되어야 하며, 8~20자의 비밀번호여야 합니다.")
	@NotBlank
	private String password;
}
