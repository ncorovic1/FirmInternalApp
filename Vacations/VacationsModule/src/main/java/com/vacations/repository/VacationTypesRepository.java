package com.vacations.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.vacations.models.Vacation;
import com.vacations.models.VacationType;

import java.sql.Date;
import java.util.List;

public interface VacationTypesRepository {

		List<Vacation> getAll();
		List<Vacation> getAllBy(int id);
		void createVacation(Date beginDate, Date endDate, int userId, int vacationType);
		void deleteVacation(int id);
}
