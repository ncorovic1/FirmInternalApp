package com.vacations.models;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class VacationType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String description;
	private int factor;
	
	protected VacationType() {}
	
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
	public VacationType(long id, String description, int factor) {
		super();
		this.id = id;
		this.description = description;
		this.factor = factor;
	}
}
