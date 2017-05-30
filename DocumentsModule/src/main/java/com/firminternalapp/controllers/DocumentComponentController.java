package com.firminternalapp.controllers;

import com.firminternalapp.models.DocumentComponent;
import com.firminternalapp.services.DocumentComponentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/documentComponents")
public class DocumentComponentController {
	@Autowired
    private DocumentComponentService dCS;

    @RequestMapping(value= "", method = RequestMethod.GET)
    public List<DocumentComponent> findAll() {
        return dCS.findAll();
    }
    
    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public DocumentComponent findOne(@PathVariable("id") long id) {
        return dCS.findOne(id);
    }
    
    @RequestMapping(value= "/document/{document_id}", method = RequestMethod.GET)
    public List<DocumentComponent> findBy(@PathVariable("document_id") int document_id) {
        return dCS.findByCreatedBy(document_id);
    }

    @RequestMapping(value = "/{document_id}", method = RequestMethod.POST)
    public boolean save(@PathVariable long document_id, @RequestBody DocumentComponent documentComponent){
        try
        {
            return dCS.save(document_id, documentComponent);
        } 
        catch (Exception e){
        	return false;
        }
    }

    @RequestMapping(value = "", method= RequestMethod.PUT)
    public boolean update(@RequestBody DocumentComponent documentComponent) {
        try {
        	dCS.update(documentComponent);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
    
    @RequestMapping(value = "", method= RequestMethod.DELETE)
    public boolean deleteAll() {
        try {
        	dCS.deleteAll();
        	return true;
        } catch (Exception e){
            return false;
        }
    }
  
    @RequestMapping(value = "/{id}", method= RequestMethod.DELETE)
    public boolean deleteOne(@PathVariable long id) {
        try {
        	dCS.deleteOne(id);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
}
