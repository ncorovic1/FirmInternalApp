package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.UniqueRandomKey;
import com.example.models.User;
import com.example.services.MailService;
import com.example.services.UniqueRandomKeyService;
import com.example.services.UsersService;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private UniqueRandomKeyService uniqueRandomKeyService;

	@Autowired
	private MailService mailService;

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

	@RequestMapping(value = "/byfirstname/{first_name}", method = RequestMethod.GET)
	public User getUserByName(@PathVariable String first_name) {
		return usersService.getUserByFirstName(first_name);
	}

	@RequestMapping(value = "/byusername/{name}", method = RequestMethod.GET)
	public User getUserByUsername(@PathVariable String name) {
		return usersService.getUserByUsername(name);
	}

	@RequestMapping(value = "/byemail/{email:.+}", method = RequestMethod.GET)
	public User getUserByEmail(@PathVariable String email) {
		return usersService.getUserByEmail(email);
	}

	@RequestMapping(value = "/byteam/{teamId}", method = RequestMethod.GET)
	public List<User> getUsersByTeam(@PathVariable long teamId) {
		return usersService.getUsersByTeam(teamId);
	}

	@RequestMapping(value = "/byteam/members/{teamId}", method = RequestMethod.GET)
	public List<Long> getTeamMembers(@PathVariable long teamId) {

		List<Long> members = new ArrayList<>();
		List<User> allUsers = (List<User>) this.getAllUsers();

		for (User user : allUsers) {
			if (user.getTeam().getId() == teamId) {
				members.add(user.getId());
			}
		}
		return members;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addUser(@RequestHeader("Authorization") String header, @RequestBody User user) {

		usersService.addUser(header, user);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateUser(@RequestHeader("Authorization") String header, @RequestBody User user) {
		usersService.updateUser(header, user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateUserById(@RequestHeader("Authorization") String header, @PathVariable Long id, @RequestBody User user) {
		usersService.updateUserById(header, id, user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@RequestHeader("Authorization") String header, @PathVariable Long id) {
		usersService.deleteUser(header, id);
	}
	
	@RequestMapping(value = "/sendemail/{email:.+}", method = RequestMethod.POST)
	public void sendEmail(@PathVariable("email") String email) {

		User user = usersService.getUserByEmail(email);

		if (user != null) {
			uniqueRandomKeyService.save(email);
			
			UniqueRandomKey key = uniqueRandomKeyService.findLastByUserId(user.getId());
			mailService.sendResetPasswordMail(email, key.getValue().toString());
		}
	}

	@RequestMapping(value = "/resetpassword/{key}/{password}/{email:.+}", method = RequestMethod.POST)
	public void resetPassword(@PathVariable("key") String key, @PathVariable("password") String password, @PathVariable("email") String email) {

		User user = usersService.getUserByEmail(email);
		String isValid = uniqueRandomKeyService.validateUrk(key, email);
		
		if (user != null && isValid.equals("{\"success\":1}")) {
			usersService.changePassword(password, email);
		}
	}
	
	@RequestMapping(value = "/resetpw/{email:.+}", method = RequestMethod.POST)
	public void resetPassword(@PathVariable("email") String email) {

		User user = usersService.getUserByEmail(email);

		if (user != null) {
			uniqueRandomKeyService.save(email);
			UniqueRandomKey key = uniqueRandomKeyService.findLastByUserId(user.getId());

			mailService.sendResetPasswordMail(email, key.getValue().toString());
			//user.setPassword(key.getValue().toString());
			usersService.changePassword(key.toString(), email);
			uniqueRandomKeyService.validateUrk(key.getValue(), email);
		}

	}
}