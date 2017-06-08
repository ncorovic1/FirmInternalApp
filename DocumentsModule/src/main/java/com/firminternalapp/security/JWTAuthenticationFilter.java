package com.firminternalapp.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.firminternalapp.security.TokenAuthenticationService;

import org.springframework.security.core.Authentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
		res.addHeader("Access-Control-Allow-Origin", "*");
	    res.addHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	    res.addHeader("Access-Control-Max-Age", "3600");
	    res.addHeader("Access-Control-Allow-Headers"," Origin, X-Requested-With, Content-Type, Accept, AUTH-TOKEN, Authorization");
		
	    Authentication authentication = TokenAuthenticationService.getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(req, res);
	}
}