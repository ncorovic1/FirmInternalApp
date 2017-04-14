package com.example.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
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

		String docClient = sirc.getService("documents-client"); 
		String vacClient = sirc.getService("vacations-client");
		
		String url = docClient + "/users";
		//String url = "http://localhost:8084/users";
		
		String url2 = vacClient + "/users";
		//String url2 = "http://localhost:8082/users";
		RestTemplate rt = restInit();	
		rt.postForObject(url, user, User.class);
		rt.postForObject(url2, user, User.class);
	}
	
	public void updateUser(User user) {
		usersRepository.save(user);
		
		String docClient = sirc.getService("documents-client"); 
		String vacClient = sirc.getService("vacations-client");
		
		String url = docClient + "/users/{id}";
		//String url = "http://localhost:8084/users";
		
		String url2 = vacClient + "/users/{id}";
		//String url2 = "http://localhost:8082/users";
		
		//String url = "http://localhost:8084/users/{id}";
		//String url2 = "http://localhost:8082/users/{id}";
		RestTemplate rt = restInit();
		rt.put(url, user, user.getId());
		rt.put(url2, user, user.getId());
	}
	
	public void deleteUser(Long id) {
		usersRepository.delete(id);
		
		String docClient = sirc.getService("documents-client"); 
		String vacClient = sirc.getService("vacations-client");
		
		String url = docClient + "/users/{id}";
		//String url = "http://localhost:8084/users";
		
		String url2 = vacClient + "/users/{id}";
		//String url2 = "http://localhost:8082/users";
		
		//String url = "http://localhost:8084/users/{id}";
		//String url2 = "http://localhost:8082/users/{id}";
	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", String.valueOf(id));
	     
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(url,  params);
	    restTemplate.delete(url2,  params);
	}
	
}
