package com.vacations.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.vacations.models.Vacation;

import java.sql.Date;
import java.util.List;

@RepositoryRestResource
public interface VacationRepository extends CrudRepository<Vacation, Long> {
	List<Vacation> getAll();
	List<Vacation> getAllBy(int id);
	boolean createVacation(Date beginDate, Date endDate, int userId, int vacationType);
	Vacation editVacation(int id, Vacation vacation);
	void deleteVacation(int id);
}
