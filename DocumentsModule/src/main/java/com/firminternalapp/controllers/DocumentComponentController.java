package com.firminternalapp.controllers;

import com.firminternalapp.models.DocumentComponent;
import com.firminternalapp.repository.DocumentComponentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/documentComponents")
public class DocumentComponentController {
	@Autowired
    private DocumentComponentRepository documentComponentsRepository;

    @RequestMapping(value= "/", method = RequestMethod.GET)
    public Iterable<DocumentComponent> findAll() {
    	Iterable<DocumentComponent> documentComponents = documentComponentsRepository.findAll();
        return documentComponents;
    }
    
    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public DocumentComponent findOne(@PathVariable("id") long id) {
    	DocumentComponent document =  documentComponentsRepository.findOne(id);
        return document;
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean save(@RequestParam long document_id, long type, String text){
        try
        {
        	DocumentComponent documentComponent = new DocumentComponent();
            documentComponentsRepository.save(documentComponent);
            return true;
        } 
        catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public boolean delete(@RequestParam long id) {
        try {
        	documentComponentsRepository.delete(id);
        	return true;
        }catch (Exception e){
            return false;
        }
    }
}
