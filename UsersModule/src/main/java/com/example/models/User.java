<<<<<<< HEAD:UsersModule/src/main/java/com/example/models/User.java
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
=======
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
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private long Id;
	
	private String name;
	
	private String lastname;
	
	private String email;
	
	private String sex;
	
	private String phone;
	
	private String address;
	
	private Date dateOfBirth;
	
	private Integer vacationDaysLeft;
	
	private String role;
	
	public long getId(){
		
		return Id;
	}
	
	public void setId(long id){
		
		Id=id;
	}
	
	public String getName(){
	
		return name;
	}
	
	public void setName(String n){
	
		name=n;
	}
	
	public String getLastname(){
		
		return lastname;
	}
	
	public void setLastname(String l){
	
		lastname=l;
	}
	
	public String getEmail(){
		
		return email;
	}
	
	public void setEmail(String e){
	
		email=e;
	}
	
	public String getSex(){
		
		return sex;
	}
	
	public void setSex(String s){
	
		sex=s;
	}
	
	public String getPhone(){
		
		return phone;
	}
	
	public void setPhone(String p){
	
		phone=p;
	}
	
	public String getAddress(){
		
		return address;
	}
	
	public void setAddress(String a){
	
		address=a;
	}
	
	public Date getDateOfBirth(){
		
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date b){
	
		dateOfBirth=b;
	}
	
	public Integer getVacationDaysLeft(){
		
		return vacationDaysLeft;
	}
	
	public void setVacationDaysLeft(Integer i){
	
		vacationDaysLeft=i;
	}
	
	public String getRole(){
		
		return role;
	}
	
	public void setRole(String r){
	
		role=r;
	}
	
	public User() {}
}
>>>>>>> 258995b77d7f0b601d5881e3e9e5c26ee33cbd8e:Users/UsersModule/src/main/java/com/example/models/User.java
