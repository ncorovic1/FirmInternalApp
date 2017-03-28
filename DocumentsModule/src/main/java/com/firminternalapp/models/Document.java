package com.firminternalapp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="documents")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable=true, length=100)
	private String title;
	
	@Column(nullable=true, length=3000)
	private String content;
	
	@Column(nullable=true)
	private long createdBy;
	
	@Column(nullable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(nullable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date modifiedAt;
	
	public Document() {
		super();
	}
	
	public Document(String t, String c, long cb, Date ca, Date ma) {
		super();
		this.setTitle(t);
		this.setContent(c);
		this.setCreatedBy(cb);
		this.setCreatedAt(ca);
		this.setModifiedAt(ma);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
