package com.springboot.todolist.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class OauthRedirectFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println(((HttpServletRequest) request).getRequestURI());
		System.out.println(((HttpServletRequest) request).getMethod());
		
		chain.doFilter(request, response);
	}
}
