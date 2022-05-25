package com.springboot.study.service.user;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.study.config.auth.PrincipalDetails;
import com.springboot.study.domain.user.User;
import com.springboot.study.web.dto.UpdateUserReqDto;

public interface AccountService {
	public boolean updateProfileImg(MultipartFile file, PrincipalDetails principalDetails);
	public boolean updateProfile(User user);
	public User getUserByUsercode(int usercode);
}
