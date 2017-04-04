package com.vacations.models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Vacation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date beginDate;
	private Date endDate;
	private int vacationType;
	
	protected Vacation() {}
	
	public Date getBeginDate() {
		return beginDate;
	}
	public int getVacationType()
	{
		return vacationType;
	}
	public int setVacationType(int id)
	{
		return this.vacationType=id;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Vacation(long id, Date beginDate, Date endDate, int vac) {
		super();
		this.id = id;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}
}
