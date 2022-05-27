package com.springboot.todolist.dto;

import lombok.NoArgsConstructor;

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

	private String username;
	private String password;
	
	public User toEntity() {
		return User.builder().username(username)
							 .password(password)
							 .build();
	}
}
