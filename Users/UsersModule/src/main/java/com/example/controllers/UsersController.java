package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.User;
import com.example.repositories.UsersRepository;

@RestController()
public class UsersController {
	
	private UsersRepository usersRepository;
	
	@RequestMapping("/hellouser")
	public String hello() {
		return "Hello user!";
	}
    
	@RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<User> getAllUsers() {
    	List<User> users = new ArrayList<>();
		usersRepository.findAll().forEach(users::add);
    	return users;
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
    	return usersRepository.findOne(id);    }
	
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
    	usersRepository.save(user);
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
    	usersRepository.save(user);
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
    	usersRepository.delete(id);
    }
	
}

