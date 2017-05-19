package com.firminternalapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private long id;
	
	//@Column(nullable=true, length=30)
	private String firstName;

	//@Column(nullable=true, length=50)
	private String lastName;

	//@Column(nullable=true, length=256)
	private String email;

	//@Column(nullable=true, length=6)
	private String gender;
	
	//@Column(nullable=true, length=20)
	private String role;
	
	public User() {}
		
	public User(long id, String firstName, String lastName, String email, String gender, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.role = role;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
