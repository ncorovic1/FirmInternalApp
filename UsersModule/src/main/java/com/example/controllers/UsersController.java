package com.example.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.User;
import com.example.services.UsersService;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		usersService.getAllUsers().forEach(users::add);
		return users;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable Long id) {
		return usersService.getUserById(id);
	}
	
	@RequestMapping(value = "/byname/{name}", method = RequestMethod.GET)
	public User getUserByName(@PathVariable String name) {
	    return usersService.getUserByFirstName(name);
	}
	    
	@RequestMapping(value = "/byemail/{email}", method = RequestMethod.GET)
	public User getUserByEmail(@PathVariable String email) {
	    	return usersService.getUserByEmail(email);
	}
	
	@RequestMapping(value = "/byteam/{team_id}", method = RequestMethod.GET)
	public List<User> getUsersByTeam(@PathVariable long team_id) {
		return usersService.getUsersByTeam(team_id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addUser(@RequestHeader("Authorization") String header, @RequestBody User user) {
		
		usersService.addUser(header, user);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateUser(@RequestHeader("Authorization") String header, @RequestBody User user) {
		usersService.updateUser(header, user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@RequestHeader("Authorization") String header, @PathVariable Long id) {
		usersService.deleteUser(header, id);
	}
}
