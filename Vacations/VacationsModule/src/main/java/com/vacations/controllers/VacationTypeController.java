package com.vacations.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vacations.models.VacationType;
import com.vacations.services.VacationTypesService;

@RestController
@RequestMapping(value = "/vacationTypes")
public class VacationTypeController {
	@Autowired
	private VacationTypesService vacationTypesService;

	@RequestMapping(value = "/vacationTypes", method = RequestMethod.GET)
	public Iterable<VacationType> getAllVacatios() {
		List<VacationType> vacationTypes = new ArrayList<>();
		vacationTypesService.getAllVacationTypes().forEach(vacationTypes::add);
		return vacationTypes;
	}

	@RequestMapping(value = "/vacationTypes/{id}", method = RequestMethod.GET)
	public VacationType getVacationById(@PathVariable Long id) {
		return vacationTypesService.getVacationTypeById(id);
	}

	@RequestMapping(value = "/vacationTypes", method = RequestMethod.POST)
	public void addVacation(@RequestBody VacationType vacationType) {
		vacationTypesService.addVacationType(vacationType);
	}

	@RequestMapping(value = "/vacationTypes/{id}", method = RequestMethod.PUT)
	public void updateVacation(@PathVariable Long id, @RequestBody VacationType vacationType) {
		vacationTypesService.updateVacationType(vacationType);
	}

	@RequestMapping(value = "/vacationTypes/{id}", method = RequestMethod.DELETE)
	public void deleteVacation(@PathVariable Long id) {
		vacationTypesService.deleteVacationType(id);
	}

}
