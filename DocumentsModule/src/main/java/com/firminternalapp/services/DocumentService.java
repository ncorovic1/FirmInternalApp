package com.firminternalapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.firminternalapp.models.Document;
import com.firminternalapp.repositories.DocumentRepository;

@Service
public class DocumentService {
	@Autowired
    private DocumentRepository documentRepository;
	
    public List<Document> findAll() {
    	List<Document> documents = documentRepository.findAll();
        return documents;
    }
    
    public Document findOne(long id) {
    	Document document =  documentRepository.findOne(id);
        return document;
    }

    public List<Document> findByCreatedBy(long author_id) {
		return documentRepository.findByCreatedBy(author_id);
	}
    
//    public Page<Document> findWithPageable(int page, int size) {
//		return documentRepository.findAll(new PageRequest(page, size, Direction.DESC, "id"));
//	}

    public boolean save(Document document){
        try
        {
            documentRepository.save(document);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean update(Document document) {
        try {
        	documentRepository.save(document);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean deleteAll() {
        try {
        	documentRepository.deleteAll();
        	return true;
        } catch (Exception e){
            return false;
        }
    }
  
    public boolean deleteOne(long id) {
        try {
        	documentRepository.delete(id);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
        
	public String welcome(String id) {
        String format = "Hello, %s!";
        String title = "documents";
        if( id != "documents")
        	title = this.findOne(Integer.parseInt(id)).getTitle();
		String message = "<br><div style='text-align:center;'>"
				+ "<h3> Welcome to " + String.format(format, title) + "</h3></div><br><br>";
		return message;
	}
	
}