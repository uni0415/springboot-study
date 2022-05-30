package com.springboot.todolist.domain;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserRepository {

	public int signup(User user);
	
	public String selectPassword(String username);
	
	public User loadUserByUsername(String username);
	
	public int checkUsername(String username);
	
	public String getNameByUsercode(int usercode);
	
	public User findOAuth2UserByOAuth2Username(String oAuth2_username);
	
	public int insertUser(User user);
}
