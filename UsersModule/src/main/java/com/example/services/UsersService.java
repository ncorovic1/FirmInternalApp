package com.example.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.models.User;
import com.example.repositories.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}
	
	public User getUserById(Long id) {
		return usersRepository.findOne(id);
	}
	
	public User getUserByName(String name) {
		return usersRepository.findByName(name);
	}
	
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	
	public void addUser(User user) {
		usersRepository.save(user);
	}
	
	public void updateUser(User user) {
		usersRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		usersRepository.delete(id);
	}
	
}
