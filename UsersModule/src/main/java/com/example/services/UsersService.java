package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.models.User;
import com.example.repositories.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
		
	@Bean
	@LoadBalanced
	private RestTemplate restInit() {
		return new RestTemplate();
	}


	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}
	
	public User getUserById(Long id) {
		return usersRepository.findOne(id);
	}
	
	public User getUserByIme(String name) {
		return usersRepository.findByFirstName(name);
	}
	
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	
	public void addUser(User user) {
		usersRepository.save(user);

		String url = "http://localhost:8084/users";
		RestTemplate rt = restInit();	
		rt.postForObject(url, user, User.class );
	}
	
	public void updateUser(User user) {
		usersRepository.save(user);
		
		String url = "http://localhost:8084/authors";
		RestTemplate rt = restInit();	
//		rt.put(url, request, uriVariables);
//		rt.put(url, user);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>(); 
		headers.add("Content-Type", "application/json");

		HttpEntity<Object> entity = new HttpEntity<Object>(user, headers); 

		ResponseEntity<User> response = rt.exchange(url, HttpMethod.PUT, entity, User.class); 
	}
	
	public void deleteUser(Long id) {
		usersRepository.delete(id);
	}
	
}
