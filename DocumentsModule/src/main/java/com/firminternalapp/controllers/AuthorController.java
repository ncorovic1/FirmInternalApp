package com.firminternalapp.controllers;

import com.firminternalapp.models.Author;
import com.firminternalapp.services.AuthorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {
    
	@Autowired
	private AuthorService aS;
	
    @RequestMapping(value= "", method = RequestMethod.GET)
    public List<Author> findAll() {
        return aS.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author findOne(@PathVariable("id") long id) {
        return aS.findOne(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public boolean save(@RequestBody Author author){
        try
        {
            aS.save(author);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public boolean update(@RequestBody Author author) {
        try {
        	aS.update(author);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
    
    @RequestMapping(value = "", method= RequestMethod.DELETE)
    public boolean deleteAll() {
        try {
        	aS.deleteAll();
        	return true;
        } catch (Exception e){
            return false;
        }
    }
  
    @RequestMapping(value = "/{id}", method= RequestMethod.DELETE)
    public boolean deleteOne(@PathVariable long id) {
        try {
        	aS.deleteOne(id);
        	return true;
        } catch (Exception e){
            return false;
        }
    }

}
