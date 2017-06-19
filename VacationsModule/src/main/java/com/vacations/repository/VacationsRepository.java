package com.vacations.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.vacations.models.Vacation;
public interface VacationsRepository extends JpaRepository<Vacation, Long> {

	Vacation findByBeginDate(Date date);
	
	List<Vacation> findByVacationTypeId(long id);
	
	@Modifying	
	@Transactional
	void deleteByUserId(long id);
}
