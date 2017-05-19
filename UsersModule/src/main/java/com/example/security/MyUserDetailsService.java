package com.example.security;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.repositories.UsersRepository;
import com.example.models.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		User user = usersRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(authority);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}
}
