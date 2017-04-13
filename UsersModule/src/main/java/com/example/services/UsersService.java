package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
		RestTemplate rt = restInit();
		
		String url = "http://localhost:8084/users";
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
//		map.add("User", user);

//		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

		rt.postForObject(url, user, User.class );
	}
	
	public void updateUser(User user) {
		usersRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		usersRepository.delete(id);
	}
	
}
