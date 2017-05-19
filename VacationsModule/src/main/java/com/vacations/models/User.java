package com.vacations.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
	private String gender;
	
	private Integer daysOfVacation;
	
	@Column(nullable=true, length=20)
	private String role;
	
	public User() {
		super();
	}
	
	/*@JsonCreator
	public User(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("email") String email, @JsonProperty("gender") String gender, @JsonProperty("daysOfVacation") Integer daysOfVacation, @JsonProperty("role") String role) {
		super();
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setGender(gender);
		this.setDaysOfVacation(daysOfVacation);
		this.setRole(role);
	}*/
	
	/*public User(String firstName, String lastName, String email, String gender, Integer daysOfVacation,
			String role) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setGender(gender);
		this.setDaysOfVacation(daysOfVacation);
		this.setRole(role);
	}*/

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getDaysOfVacation() {
		return daysOfVacation;
	}

	public void setDaysOfVacation(Integer daysOfVacation) {
		this.daysOfVacation = daysOfVacation;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
