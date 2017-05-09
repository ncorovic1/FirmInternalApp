package com.example.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="uposlenik")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="ime")
	private String firstName;
	
	@Column(name="prezime")
	private String lastName;

	private String email;
	
	@Column(name="spol")
	private String sex;

	private String telefon;

	private String adresa;

	private Date datumRodjenja;

	private Integer danaOdmora;
	
	@Column(name="rola")
	private String role;
	
	@ManyToOne
	private Team team;
	
	public User() {}
	
	@Override
    public String toString() {
        return "{" +
	                "id=" + this.id +
	                ", firstName='" + this.firstName + '\'' +
	                ", lastName='" + this.lastName + '\'' +
	                ", email='" + this.email + '\'' +
	                ", sex='" + this.sex + '\'' +
	                ", telefon='" + this.telefon + '\'' +
	                ", adresa='" + this.adresa + '\'' +
	                ", datumRodjena=" + this.datumRodjenja +
	                ", danaOdmora=" + this.danaOdmora +
	                ", role=" + this.role + '\'' +
                '}';
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fn) {
		this.firstName = fn;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String ln) {
		this.lastName = ln;
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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Integer getDanaOdmora() {
		return danaOdmora;
	}

	public void setDanaOdmora(Integer danaOdmora) {
		this.danaOdmora = danaOdmora;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}