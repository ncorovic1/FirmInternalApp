package com.FirmInternalApp.TeamsModule.security;

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
				
				if (request.getMethod().equals("GET") || info.get(1).equals("ADMIN"))
						access = true;
				
				if (access)
					return new UsernamePasswordAuthenticationToken(info.get(0), null, emptyList());
				else 
					return new UsernamePasswordAuthenticationToken(info.get(0), null);
			}
		}
		return null;
	}
}