package com.firminternalapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firminternalapp.models.Author;
import com.firminternalapp.repositories.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
    private AuthorRepository authorRepository;
	
    public List<Author> findAll() {
    	List<Author> authors = authorRepository.findAll();
        return authors;
    }
    
    public Author findOne(long id) {
    	Author document =  authorRepository.findOne(id);
        return document;
    }

    public boolean save(Author author){
        try
        {
            authorRepository.save(author);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean update(Author author) {
        try {
        	authorRepository.save(author);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean deleteAll() {
        try {
        	authorRepository.deleteAll();
        	return true;
        } catch (Exception e){
            return false;
        }
    }
  
    public boolean deleteOne(long id) {
        try {
        	authorRepository.delete(id);
        	return true;
        } catch (Exception e){
            return false;
        }
    }	
}