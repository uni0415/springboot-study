package com.springboot.study.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SigninReqDto {
	@NotBlank
	private String username;
	
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W).{8,20}",
			message = "비밀번호는 숫자,영문자,특수기호가 하나이상 포함되어있어야 하며 8글자 이상이어야 합니다.")
	@NotBlank
	private String password;
}
