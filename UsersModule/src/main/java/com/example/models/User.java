package com.example.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;

	private String lastName;

	private String email;

	private String username;

	private String password;

	private String gender;

	private String telephone;

	private String address;

	private Date dateOfBirth;

	private Integer daysOfVacation;

	private String role;

	@ManyToOne
	private Team team;

	public User() {}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + '\''
				+ ", email='" + this.email + '\'' + ", gender='" + this.gender + '\'' + ", telephone='" + this.telephone + '\''
				+ ", address='" + this.address + '\'' + ", dateOfBirth=" + this.dateOfBirth + ", daysOfVacation="
				+ this.daysOfVacation + ", role='" + this.role + '\'' + '}';
	}
}