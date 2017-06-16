package com.vacations.models;

import java.sql.Date;

import javax.persistence.Convert;
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
	
	@Convert(converter = StatusConverter.class)
	private Status status;
	
	@ManyToOne
	private VacationType vacationType;
	
	@ManyToOne
	private User user;
	
	protected Vacation() {
		this.status = Status.PENDING;
	}

	public Vacation(long id, Date beginDate, Date endDate, Status status, VacationType vacationType, User user) {
		super();
		this.id = id;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.status = Status.PENDING;
		this.vacationType = vacationType;
		this.user = user;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
