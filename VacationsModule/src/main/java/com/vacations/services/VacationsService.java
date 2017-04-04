package com.vacations.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vacations.models.Vacation;
import com.vacations.repository.VacationsRepository;

@Service
public class VacationsService {

	@Autowired
	private VacationsRepository vacationsRepository;

	public List<Vacation> getAllVacations() {
		List<Vacation> vacations = new ArrayList<>();
		vacationsRepository.findAll().forEach(vacations::add);
		return vacations;
	}

	public Vacation getVacationsById(Long id) {
		return vacationsRepository.findOne(id);
	}

	public void addVacation(Vacation vacation) {
		vacationsRepository.save(vacation);
	}

	public void updateVacation(Long id, Vacation vacation) {
		Vacation vacationToBeUpdated = vacationsRepository.findOne(id);
		vacationToBeUpdated.setVacationType(vacation.getVacationType());
		vacationToBeUpdated.setBeginDate(vacation.getBeginDate());
		vacationToBeUpdated.setEndDate(vacation.getEndDate());
		vacationsRepository.save(vacationToBeUpdated);
	}

	public void deleteVacation(Long id) {
		vacationsRepository.delete(id);
	}

	public Vacation getVacationByType(int id) {
		return vacationsRepository.findByVacationType(id);
	}

	public Vacation getVacationByBeginDate(Date date) {
		return vacationsRepository.findByBeginDate(date);

	}

}
