package com.vacations.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacations.models.VacationType;
import com.vacations.repository.VacationTypesRepository;

@Service
public class VacationTypesService {

	@Autowired
	private VacationTypesRepository vacationTypesRepository;

	public List<VacationType> getAllVacationTypes() {
		List<VacationType> vacationTypes = new ArrayList<>();
		vacationTypesRepository.findAll().forEach(vacationTypes::add);
		return vacationTypes;
	}

	public VacationType getVacationTypeById(Long id) {
		return vacationTypesRepository.findOne(id);
	}

	public void addVacationType(VacationType vactype) {
		vacationTypesRepository.save(vactype);
	}

	public void updateVacationType(VacationType user) {
		vacationTypesRepository.save(user);
	}

	public void deleteVacationType(Long id) {
		vacationTypesRepository.delete(id);
	}

}
