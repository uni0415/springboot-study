package com.springboot.todolist.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.todolist.filter.OauthRedirectFilter;

//@Configuration
public class RedirectFilterConfig {

//	@Bean
//	public FilterRegistrationBean<OauthRedirectFilter> filter2(){
//		FilterRegistrationBean<OauthRedirectFilter> bean = new FilterRegistrationBean<OauthRedirectFilter>(new OauthRedirectFilter());
//		bean.addUrlPatterns("/*");
//		bean.setOrder(0);
//		return bean;
//	}
}
