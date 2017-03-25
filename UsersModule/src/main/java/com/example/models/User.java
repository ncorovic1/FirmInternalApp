package com.example.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "uposlenik")
public class User {
	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	
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
	
	public long getId(){
		
		return id;
	}
	
	public void setId(long Id){
		
		id=Id;
	}
	
	public String getName(){
	
		return ime;
	}
	
	public void setName(String n){
	
		ime=n;
	}
	
	public String getLastname(){
		
		return prezime;
	}
	
	public void setLastname(String l){
	
		prezime=l;
	}
	
	public String getEmail(){
		
		return email;
	}
	
	public void setEmail(String e){
	
		email=e;
	}
	
	public String getSex(){
		
		return spol;
	}
	
	public void setSex(String s){
	
		spol=s;
	}
	
	public String getPhone(){
		
		return telefon;
	}
	
	public void setPhone(String p){
	
		telefon=p;
	}
	
	public String getAddress(){
		
		return adresa;
	}
	
	public void setAddress(String a){
	
		adresa=a;
	}
	
	public Date getDateOfBirth(){
		
		return datumRodjenja;
	}
	
	public void setDateOfBirth(Date b){
	
		datumRodjenja=b;
	}
	
	public Integer getVacationDaysLeft(){
		
		return danaOdmora;
	}
	
	public void setVacationDaysLeft(Integer i){
	
		danaOdmora=i;
	}
	
	public String getRole(){
		
		return rola;
	}
	
	public void setRole(String r){
	
		rola=r;
	}
	
	public User() {}
}
