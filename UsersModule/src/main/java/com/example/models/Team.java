package com.example.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {

	@Id
	private long id;
	
	private String handle;
	
	private String name;
	
	private String info;
	
	public Team() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
