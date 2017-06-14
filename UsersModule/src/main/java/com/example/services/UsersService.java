package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.models.User;
import com.example.repositories.UsersRepository;
import com.example.controllers.*;

@Service
public class UsersService {
	
	@Autowired
	ServiceInstanceRestController sirc;
	
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
	
	public User getUserById(long id) {
		return usersRepository.findById(id);
	}
	
	public User getUserByFirstName(String firstName) {
		return usersRepository.findByFirstName(firstName);
	}
	
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	
	public User getUserByUsername(String username) {
		return usersRepository.findByUsername(username);
	}
	
	public List<User> getUsersByTeam(long teamId) {
		return usersRepository.findByTeamId(teamId);
	}
	
	public void addUser(String header, User user) {
		usersRepository.save(user);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", header);
		headers.add("Content-Type", "application/json");
		
		RestTemplate rt = restInit();
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<User> request = new HttpEntity<User>(user, headers);

		String docClient = sirc.getService("documents-client");
		String vacClient = sirc.getService("vacations-client");
		
		String url = docClient + "/users";
		String url2 = vacClient + "/users";
		
		rt.postForObject(url, request, User.class);
		rt.postForObject(url2, request, User.class);
	}
	
	public void updateUser(String header, User user) {
		usersRepository.save(user);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", header);
		headers.add("Content-Type", "application/json");
		
		RestTemplate rt = restInit();
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<User> request = new HttpEntity<User>(user, headers);
		
		String docClient = sirc.getService("documents-client"); 
		String vacClient = sirc.getService("vacations-client");
		
		String url = docClient + "/users/{id}";
		String url2 = vacClient + "/users/{id}";
		
		rt.put(url, request, user.getId());
		rt.put(url2, request, user.getId());
	}
	
	public void deleteUser(String header, Long id) {
		usersRepository.delete(id);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
	    headers.add("Authorization", header);
		
		RestTemplate rt = restInit();
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<Long> request = new HttpEntity<Long>(id, headers);
		
		String docClient = sirc.getService("documents-client"); 
		String vacClient = sirc.getService("vacations-client");
		
		String url = docClient + "/users/{id}";
		String url2 = vacClient + "/users/{id}";
		
		rt.exchange(url, HttpMethod.DELETE, request, String.class, id);
		rt.exchange(url2, HttpMethod.DELETE, request, String.class, id);
	}
	
}
