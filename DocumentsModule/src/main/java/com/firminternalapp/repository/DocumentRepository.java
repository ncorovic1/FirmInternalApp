package com.firminternalapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.firminternalapp.models.Document;

import java.sql.Date;
import java.util.List;

@RepositoryRestResource
public interface DocumentRepository extends CrudRepository<Document, Long> {

	List<Document> getAll(); 
	Document getOne(int id);
	void createDocument(String title, String content, long created_by, Date created_at, Date modified_at);
	void deleteDocument(long id);

}