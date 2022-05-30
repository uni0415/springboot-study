package com.springboot.study.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.study.filter.TestFilter1;
import com.springboot.study.filter.TestFilter2;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<TestFilter2> filter2(){
		FilterRegistrationBean<TestFilter2> bean = new FilterRegistrationBean<TestFilter2>(new TestFilter2());
		bean.addUrlPatterns("/*");
		bean.setOrder(0);
		return bean;
	}
}
