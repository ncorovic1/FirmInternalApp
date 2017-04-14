package com.vacations.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class User {

	@Id
	private long id;
	
	@Column(nullable=true, length=30)
	private String firstName;

	@Column(nullable=true, length=50)
	private String lastName;

	@Column(nullable=true, length=256)
	private String email;

	@Column(nullable=true, length=6)
	private String sex;
	
	@Column(nullable=true, length=20)
	private String role;
	
	private Integer danaOdmora;
	
	public User() {
		super();
	}
	
	public User(String fn, String ln, String em, String s, String r, Integer dod) {
		super();
		this.setFirstName(fn);
		this.setLastName(ln);
		this.setEmail(em);
		this.setSex(s);
		this.setRole(r);
		this.setDanaOdmora(dod);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getDanaOdmora() {
		return danaOdmora;
	}

	public void setDanaOdmora(Integer danaOdmora) {
		this.danaOdmora = danaOdmora;
	}
}
