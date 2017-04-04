package com.vacations.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vacations.models.Vacation;
public interface VacationsRepository extends JpaRepository<Vacation, Long> {

	Vacation findByBeginDate(Date date);

	Vacation findByVacationType(int id);
}
