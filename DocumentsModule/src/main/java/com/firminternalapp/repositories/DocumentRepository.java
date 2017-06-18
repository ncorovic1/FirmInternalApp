package com.firminternalapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.firminternalapp.models.Document;

@RepositoryRestResource
public interface DocumentRepository extends JpaRepository<Document, Long> {
	public List<Document> findByAuthorId(long author_id);
	
	@Modifying	
	@Transactional
	void deleteByAuthorId(long id);
}