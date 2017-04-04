package com.example.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.models.User;
import com.example.services.UsersService;

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	@RequestMapping("/hellouser")
	public String hello() {
		return "Hello user!";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Iterable<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		usersService.getAllUsers().forEach(users::add);
		return users;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable Long id) {
		return usersService.getUserById(id);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		usersService.addUser(user);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public void updateUser(@PathVariable Long id, @RequestBody User user) {
		usersService.updateUser(user);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long id) {
		usersService.deleteUser(id);
	}
	
	@RequestMapping(value = "/byname/{name}", method = RequestMethod.GET)
	public User getUserByName(@PathVariable String name) {
	    return usersService.getUserByIme(name);
	    }
	    
	@RequestMapping(value = "/byemail/{email}", method = RequestMethod.GET)
	public User getUserByEmail(@PathVariable String email) {
	    	return usersService.getUserByEmail(email);
	    }
	    
	

}
