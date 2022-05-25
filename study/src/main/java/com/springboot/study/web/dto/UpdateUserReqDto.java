package com.springboot.study.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.springboot.study.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserReqDto {
	private int usercode;
	private String username;
	private String email;
	private String name;
	
	
	public User toUpdateUserEntity() {
		return User.builder()
				.usercode(usercode)
				.username(username)
				.email(email)
				.name(name)
				.build();
	}
}
