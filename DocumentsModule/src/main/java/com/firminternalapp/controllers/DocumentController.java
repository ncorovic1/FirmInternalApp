package com.firminternalapp.controllers;

import com.firminternalapp.models.Document;
import com.firminternalapp.services.DocumentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/documents")
public class DocumentController {
    
	@Autowired
	private DocumentService dS;
	
    @RequestMapping(value= "", method = RequestMethod.GET)
    public List<Document> findAll() {
        return dS.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Document findOne(@PathVariable("id") long id) {
        return dS.findOne(id);
    }

    @RequestMapping(value= "/author/{author}", method = RequestMethod.GET)
    public List<Document> findBy(@PathVariable("author") int author) {
        return dS.findByCreatedBy(author);
    }
    
    @RequestMapping(value= "/page", method = RequestMethod.GET)
    public Document findWithPageable(@RequestParam("size") int size, @RequestParam("page")int page) {
    	return new Document();
    	//return dS.findWithPageable(page, size);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public boolean save(@RequestBody Document document){
        try
        {
            dS.save(document);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    @RequestMapping(method= RequestMethod.PUT)
    public boolean update(@RequestBody Document document) {
        try {
        	dS.update(document);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
    
    @RequestMapping(value = "", method= RequestMethod.DELETE)
    public boolean deleteAll() {
        try {
        	dS.deleteAll();
        	return true;
        } catch (Exception e){
            return false;
        }
    }
  
    @RequestMapping(value = "/{id}", method= RequestMethod.DELETE)
    public boolean deleteOne(@PathVariable long id) {
        try {
        	dS.deleteOne(id);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
        
    @RequestMapping("/welcome")
	public String welcome(@RequestParam(value="id", defaultValue="documents", required=false) String id) {
		return dS.welcome(id);
	}
}
