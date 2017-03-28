package com.firminternalapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable=true, length=30)
	private String name;

	@Column(nullable=true, length=50)
	private String lastName;

	@Column(nullable=true, length=256)
	private String email;

	@Column(nullable=true, length=6)
	private String sex;
	
	public Author() {
		super();
	}
	
	public Author(String n, String ln, String em, String s) {
		super();
		this.setName(n);
		this.setLastName(ln);
		this.setEmail(em);
		this.setSex(s);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
