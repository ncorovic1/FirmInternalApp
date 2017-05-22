package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.services.UniqueRandomKeyService;

@RestController
@RequestMapping(value = "/urk")
public class UniqueRandomKeyController {
	
	@Autowired
	private UniqueRandomKeyService urkService;
    
    @RequestMapping(value = "/validate/{urk}/{email:.+}", method = RequestMethod.GET)
    public String validateUrk(@PathVariable String urk, @PathVariable String email) {
    	return urkService.validateUrk(urk, email);
    }
    
    @RequestMapping(value = "/{email:.+}", method = RequestMethod.POST)
    public String save(@PathVariable String email){
    	return urkService.save(email);
    }
}
