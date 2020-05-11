package com.example.microservice.springeurekaclientschoolservice.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.microservice.springeurekaclientschoolservice.mdc.MDCUtil;
import com.example.microservice.springeurekaclientschoolservice.mdc.TokenInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CertificationInterceptor implements Filter{

	@Autowired
	public TokenInfo tokenInfo;
	
	@Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String accessToken = httpServletRequest.getHeader("Authorization");
		tokenInfo.setAccessToken(accessToken);
		MDCUtil.setAccessToken(accessToken);
		System.out.println("token start ============== token start");
		System.out.println(accessToken);
		System.out.println(MDCUtil.getAccessToken());
		System.out.println("token end ============== token end");
		chain.doFilter(request, res);
	}
}
