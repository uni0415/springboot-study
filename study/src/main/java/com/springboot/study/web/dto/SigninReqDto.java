package com.springboot.study.web.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SigninReqDto {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
