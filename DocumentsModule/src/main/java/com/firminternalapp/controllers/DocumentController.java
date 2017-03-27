package com.firminternalapp.controllers;

import com.firminternalapp.models.Document;
import com.firminternalapp.repository.DocumentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/documents")
public class DocumentController {
	@Autowired
    private DocumentRepository documentsRepository;
    
    @RequestMapping(value= "", method = RequestMethod.GET)
    public List<Document> findAll() {
    	List<Document> documents = documentsRepository.findAll();
        return documents;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Document findOne(@PathVariable("id") long id) {
    	Document document =  documentsRepository.findOne(id);
        return document;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public boolean save(@RequestBody Document document){
        try
        {
            documentsRepository.save(document);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    @RequestMapping(value = "", method= RequestMethod.PUT)
    public boolean update(@RequestBody Document document) {
        try {
        	documentsRepository.save(document);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
    
    @RequestMapping(value = "", method= RequestMethod.DELETE)
    public boolean deleteAll() {
        try {
        	documentsRepository.deleteAll();
        	return true;
        } catch (Exception e){
            return false;
        }
    }
  
    @RequestMapping(value = "/{id}", method= RequestMethod.DELETE)
    public boolean deleteOne(@PathVariable long id) {
        try {
        	documentsRepository.delete(id);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
        
    @RequestMapping("/welcome")
	public String welcome(@RequestParam(value="id", defaultValue="documents", required=false) String id) {
        String format = "Hello, %s!";
        String title = this.findOne(Integer.parseInt(id)).getTitle();
		String message = "<br><div style='text-align:center;'>"
				+ "<h3> Welcome to " + String.format(format, title) + "! </h3></div><br><br>";
		return message;
	}
}
