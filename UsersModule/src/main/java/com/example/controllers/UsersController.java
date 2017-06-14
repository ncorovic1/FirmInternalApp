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
//import com.example.services.ApplicationMailer;
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

	@RequestMapping(value = "/byusername/{username}", method = RequestMethod.GET)
	public User getUserByUsername(@PathVariable String username) {
		return usersService.getUserByUsername(username);
	}

	@RequestMapping(value = "/byemail/{email:.+}", method = RequestMethod.GET)
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

	/*
	 * @RequestMapping(value = "/{email}", method = RequestMethod.POST) public
	 * void sendMail(@RequestHeader("Authorization") String
	 * header, @PathVariable String email) { ApplicationContext context = new
	 * FileSystemXmlApplicationContext("application-context.xml");
	 * 
	 * //Get the mailer instance ApplicationMailer mailer = (ApplicationMailer)
	 * context.getBean("mailService"); String poruka=urks.save(email); //Send a
	 * composed mail if(poruka=="{\"success\":1}")
	 * u=usersService.getUserByEmail(email);
	 * urk=urks.findLastByUserId(u.getId()); mailer.sendMail(email,
	 * "Reset Your Password", "Your new password is"+ urk.getValue());
	 * 
	 * }
	 */

	@RequestMapping(value = "/resetpassword/{email:.+}", method = RequestMethod.POST)
	public void resetPassword(@RequestHeader("Authorization") String header, @PathVariable("email") String email) {

		User user = usersService.getUserByEmail(email);

		if (user != null) {
			UniqueRandomKey key = uniqueRandomKeyService.findLastByUserId(user.getId());

			mailService.sendResetPasswordMail(email, key.getValue().toString());
			
			user.setPassword(key.getValue().toString());
			usersService.updateUser(header, user);
			uniqueRandomKeyService.validateUrk(key.getValue(), email);
		}
	}

}