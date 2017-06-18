package com.example.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

import static java.util.Collections.emptyList;

import java.util.Arrays;

class TokenAuthenticationService {

	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	static void addAuthentication(HttpServletResponse res, String username) {
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	    res.addHeader("Access-Control-Expose-Headers","Authorization");
	}

	static Authentication getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();
			
			if (user != null) {
				List<String> info = Arrays.asList(user.split(","));
				boolean access = false;

				if (request.getMethod().equals("GET")) {
					if (info.get(1).equals("HR") || info.get(1).equals("ADMIN")) 
						access = true;
				 	else if ((request.getRequestURI().length() >= 13 && request.getRequestURI().substring(0, 13).equals("/users/byteam")) || (request.getRequestURI().length() >= 17 && request.getRequestURI().substring(0, 17).equals("/users/byusername"))) 
				 		access = true;
				} 
				else if (info.get(1).equals("ADMIN")) {
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