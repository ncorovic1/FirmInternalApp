package com.example.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uposlenik")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String ime;

	private String prezime;

	private String email;

	private String spol;

	private String telefon;

	private String adresa;

	private Date datumRodjenja;

	private Integer danaOdmora;

	private String rola;
	
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return ime;
	}


	public void setName(String name) {
		this.ime = name;
	}


	public String getLastName() {
		return prezime;
	}


	public void setLastName(String lastName) {
		this.prezime = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSex() {
		return spol;
	}


	public void setSex(String sex) {
		this.spol = sex;
	}


	public String getPhone() {
		return telefon;
	}


	public void setPhone(String phone) {
		this.telefon = phone;
	}


	public String getAddress() {
		return adresa;
	}


	public void setAddress(String address) {
		this.adresa = address;
	}


	public Date getDateOfBirth() {
		return datumRodjenja;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.datumRodjenja = dateOfBirth;
	}


	public Integer getDaysOfVacation() {
		return danaOdmora;
	}


	public void setDaysOfVacation(Integer daysOfVacation) {
		this.danaOdmora = daysOfVacation;
	}


	public String getRole() {
		return rola;
	}


	public void setRole(String role) {
		this.rola = role;
	}
	
	public User() {}
}