package com.firminternalapp.controllers;

import com.firminternalapp.models.Document_Component;
import com.firminternalapp.repository.Document_ComponentRepository;

import java.util.List;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vacations")
public class Document_ComponentController {
	@Autowired
    private Document_ComponentRepository document_componentsRepository;

    @RequestMapping(value= "/", method = RequestMethod.GET)
    public List<Document_Component> getAll() {
    	List<Document_Component> document_components = document_componentsRepository.getAll();
        return document_components;
    }
    
    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public Document_Component getOne(@PathVariable("id") int id) {
    	Document_Component document =  document_componentsRepository.getOne(id);
        return document;
    }


    @RequestMapping(method = RequestMethod.POST)
    public boolean createDocument_Component(@RequestParam long document_id, long type, String text){
        try
        {
            document_componentsRepository.createDocument_Component(document_id, type, text);
            return true;
        } 
        catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value = "/delete", method= RequestMethod.POST)
    public boolean deleteDocument_Component(@RequestParam long id) {
        try {
        	document_componentsRepository.deleteDocument_Component(id);
        	return true;
        }catch (Exception e){
            return false;
        }
    }
}
