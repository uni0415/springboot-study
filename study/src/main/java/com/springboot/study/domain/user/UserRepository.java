package com.springboot.study.domain.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
	public int signup(UserMst userMst) throws Exception;
	public int isUsercode(int usercode) throws Exception;
}
