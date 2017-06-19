package com.vacations.security;

import io.jsonwebtoken.Jwts;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Collections.emptyList;

import java.util.Arrays;

class TokenAuthenticationService {

	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	static Authentication getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();

			if (user != null) {
				List<String> info = Arrays.asList(user.split(","));
				boolean access = false;
				
				if (info.get(1).equals("ADMIN") || request.getMethod().equals("GET"))
					access = true;
				else if (info.get(1).equals("HR")) {
					if (request.getRequestURI().length() >= 14 && request.getRequestURI().substring(0, 14).equals("/vacationTypes"))
						access = true;
					else if (!request.getMethod().equals("DELETE")) //Sve osim DELETE vacationsa
						access = true;
				}
				else if (info.get(1).equals("EMPLOYEE") && request.getMethod().equals("POST")) {
					if (request.getRequestURI().length() >= 10 && request.getRequestURI().substring(0, 10).equals("/vacations"))
						access = true;
				}
				else if (info.get(1).equals("EMPLOYEE") && request.getMethod().equals("GET")) {
					if (request.getRequestURI().length() >= 14 && request.getRequestURI().substring(0, 14).equals("/vacationTypes"))
						access = true;
				}
				
				if (access)
					return new UsernamePasswordAuthenticationToken(info.get(0), null, emptyList());
				else 
					return new UsernamePasswordAuthenticationToken(info.get(0), null);
			}
		}
		return null;
	}
}