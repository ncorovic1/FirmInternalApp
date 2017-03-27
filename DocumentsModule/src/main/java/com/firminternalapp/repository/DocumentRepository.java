package com.firminternalapp.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.firminternalapp.models.Document;

@RepositoryRestResource
public interface DocumentRepository extends JpaRepository<Document, Long> {
	List<Document> findByCreatedBy(long createdBy);
	
	@Query(value="select d from Document d")
	public List<Document> findWithPageable(Pageable pageable);
	//Use in Controller like: repository.findWithPageable(new PageRequest(0, 10, Direction.DESC, "id"));
}