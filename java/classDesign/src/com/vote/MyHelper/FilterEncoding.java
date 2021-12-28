package com.vote.MyHelper;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterEncoding{
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain filterChain) throws IOException,ServletException{
		request.setCharacterEncoding("utf-8");
		filterChain.doFilter(request, response);
		response.setContentType("text/html;charset=utf-8");
	}
	public void init(FilterConfig filterConfig)throws ServletException{}
}
