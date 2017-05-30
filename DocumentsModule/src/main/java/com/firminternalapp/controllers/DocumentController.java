package com.firminternalapp.controllers;

import com.firminternalapp.models.Document;
import com.firminternalapp.services.DocumentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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

    @RequestMapping(value= "/author/{author_id}", method = RequestMethod.GET)
    public List<Document> findByAuthorId(@PathVariable("author_id") long author_id) {
        return dS.findByAuthorId(author_id);
    }
    
    @RequestMapping(value= "/{columnName}/{direction}/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public Page<Document> findWithPageable(@PathVariable String columnName, @PathVariable String direction,
    										@PathVariable Integer pageSize, @PathVariable Integer pageNumber) { 	
    	return dS.findWithPageable(columnName, direction, pageSize, pageNumber);
    }
    
    @RequestMapping(value="{author_id}", method = RequestMethod.POST)
    public boolean save(@PathVariable long author_id, @RequestBody Document document){
        try
        {
            dS.save(author_id, document);
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
