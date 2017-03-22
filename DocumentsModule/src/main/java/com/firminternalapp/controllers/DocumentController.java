package com.firminternalapp.controllers;

import com.firminternalapp.models.Document;
import com.firminternalapp.repository.DocumentRepository;

import java.util.List;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vacations")
public class DocumentController {
	@Autowired
    private DocumentRepository documentsRepository;

    @RequestMapping(value= "/", method = RequestMethod.GET)
    public List<Document> getAll() {
    	List<Document> documents = documentsRepository.getAll();
        return documents;
    }
    
    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public Document getOne(@PathVariable("id") int id) {
    	Document document =  documentsRepository.getOne(id);
        return document;
    }


    @RequestMapping(method = RequestMethod.POST)
    public boolean createDocument(@RequestParam String title, String content, long created_by, Date created_at, Date modified_at){
        try
        {
            documentsRepository.createDocument(title, content, created_by, created_at, modified_at);
            return true;
        } 
        catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value = "/delete", method= RequestMethod.POST)
    public boolean deleteDocument(@RequestParam long id) {
        try {
        	documentsRepository.deleteDocument(id);
        	return true;
        }catch (Exception e){
            return false;
        }
    }
}
