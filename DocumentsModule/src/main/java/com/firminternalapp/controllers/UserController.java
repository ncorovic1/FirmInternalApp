package com.firminternalapp.controllers;

import com.firminternalapp.models.User;
import com.firminternalapp.services.UserService;

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
}
