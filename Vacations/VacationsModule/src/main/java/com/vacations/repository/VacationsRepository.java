package com.vacations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vacations.models.Vacation;

public interface VacationsRepository extends JpaRepository<Vacation, Long> {

}
