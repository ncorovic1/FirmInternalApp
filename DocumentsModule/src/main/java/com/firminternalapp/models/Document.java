package com.firminternalapp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="documents")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private Author author;
	
	@Column(nullable=true, length=100)
	private String title;
	
	@Column(nullable=true, length=3000)
	private String content;
	
	@Column(nullable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(nullable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date modifiedAt;
	
	public Document() {
		super();
	}
	
	public Document(String t, String c, Author a, Date ca, Date ma) {
		super();
		this.setAuthor(a);
		this.setTitle(t);
		this.setContent(c);
		this.setCreatedAt(ca);
		this.setModifiedAt(ma);
	}
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
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
