package com.vacations.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vacation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date beginDate;
	private Date endDate;
	
	@ManyToOne
	private VacationType vacationType;
	
	@ManyToOne
	private User user;
	
	protected Vacation() {}
	
	public Vacation(Date beginDate, Date endDate, VacationType vac, User u) {
		super();
		this.beginDate    = beginDate;
		this.endDate      = endDate;
		this.vacationType = vac;
		this.user         = u;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Date getBeginDate() {
		return beginDate;
	}
	
	public VacationType getVacationType()
	{
		return vacationType;
	}
	
	public VacationType setVacationType(VacationType vt)
	{
		return this.vacationType = vt;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
