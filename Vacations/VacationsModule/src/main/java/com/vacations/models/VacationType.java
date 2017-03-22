package com.vacations.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VacationType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String description;
	private int factor;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFactor() {
		return factor;
	}

	public void setFactor(int factor) {
		this.factor = factor;
	}
}
