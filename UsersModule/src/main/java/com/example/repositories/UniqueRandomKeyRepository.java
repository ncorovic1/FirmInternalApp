package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.example.models.*;

@RepositoryRestResource
public interface UniqueRandomKeyRepository extends JpaRepository<UniqueRandomKey, Long> {
	UniqueRandomKey findFirstByUserIdOrderByIdDesc(long user_id);

	UniqueRandomKey findByValue(String value);
	
	@Modifying	
	@Transactional
	void deleteByUserId(long id);
}