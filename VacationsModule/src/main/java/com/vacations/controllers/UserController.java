package com.vacations.controllers;

import com.vacations.models.User;
import com.vacations.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usersController")
public class UserController {

	@Autowired
	private UserService uS;

	@RequestMapping(method = RequestMethod.GET)
	public List<User> findAll() {
		return uS.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findOne(@PathVariable("id") long id) {
		return uS.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public boolean save(@RequestBody User user) {
		try {
			uS.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public boolean update(@RequestBody User user) {
		try {
			uS.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public boolean deleteAll() {
		try {
			uS.deleteAll();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteOne(@PathVariable long id) {
		try {
			uS.deleteOne(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
