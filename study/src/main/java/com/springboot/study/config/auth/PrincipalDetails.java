package com.springboot.study.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.springboot.study.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //권한을 담고있는 Collection
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		user.getRoleList().forEach(r -> {
			authorities.add(()-> r);
		});
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() { //계정이 만료되었는지 확인
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() { //비밀번호가 지정한 횟수 이상 틀리면 잠김
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() { //자격 증명이 만료가 되면 계정사용 불가
		return true;
	}
	
	@Override
	public boolean isEnabled() { //휴면계정
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
