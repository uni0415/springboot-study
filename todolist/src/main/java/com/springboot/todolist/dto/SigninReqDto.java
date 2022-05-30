package com.springboot.todolist.dto;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import com.springboot.todolist.domain.User;

import lombok.AllArgsConstructor;

import lombok.ToString;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SigninReqDto {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	
	public User toEntity() {
		return User.builder().username(username)
							 .password(password)
							 .build();
	}
}
