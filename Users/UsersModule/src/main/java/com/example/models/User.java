package com.example.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
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
