package com.firminternalapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="document_components")
public class DocumentComponent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private Document document;

	@Column(nullable=true)
	private short type;
	
	@Column(nullable=true, length=500)
	private String text;
	
	public DocumentComponent() {
		super();
	}
	
	public DocumentComponent(Document d, short ty, String te) {
		super();
		this.setDocument(d);
		this.setType(ty);
		this.setText(te);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Document getDocument() {
		return document;
	}
	
	public void setDocument(Document document) {
		this.document = document;
	}
	
	public short getType() {
		return type;
	}
	
	public void setType(short type) {
		this.type = type;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
