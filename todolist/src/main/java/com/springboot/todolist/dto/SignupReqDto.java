package com.springboot.todolist.dto;

import com.springboot.todolist.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupReqDto {

	private String name;
	private String username;
	private String password;
	
	public User toUserEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.build();
	}
}
