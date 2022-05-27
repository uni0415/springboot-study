package com.springboot.todolist.domain;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

	public int signup(User user);
	
	public String selectPassword(String username);
	
	public User loadUserByUsername(String username);
}
