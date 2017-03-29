package com.firminternalapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firminternalapp.models.Document;
import com.firminternalapp.models.DocumentComponent;
import com.firminternalapp.repositories.DocumentComponentRepository;

@Service
public class DocumentComponentService {
	@Autowired
    private DocumentComponentRepository documentComponentRepository;
	@Autowired
	private DocumentService documentService;
	
    public List<DocumentComponent> findAll() {
    	List<DocumentComponent> documentComponents = documentComponentRepository.findAll();
        return documentComponents;
    }
    
    public DocumentComponent findOne(long id) {
    	DocumentComponent documentComponent =  documentComponentRepository.findOne(id);
        return documentComponent;
    }

    public List<DocumentComponent> findByCreatedBy(long document_id) {
		return documentComponentRepository.findByDocumentId(document_id);
	}
    
    public boolean save(long document_id, DocumentComponent documentComponent){
        try
        {
        	Document d = documentService.findOne(document_id);
        	documentComponent.setDocument(d);       	
        	
        	documentComponentRepository.save(documentComponent);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean update(DocumentComponent documentComponent) {
        try {
        	documentComponentRepository.save(documentComponent);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean deleteAll() {
        try {
        	documentComponentRepository.deleteAll();
        	return true;
        } catch (Exception e){
            return false;
        }
    }
  
    public boolean deleteOne(long id) {
        try {
        	documentComponentRepository.delete(id);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
}