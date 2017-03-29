package com.vacations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vacations.models.VacationType;
public interface VacationTypesRepository extends JpaRepository<VacationType, Long> {
}
