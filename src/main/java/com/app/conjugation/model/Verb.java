package com.app.conjugation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "verb")
public class Verb {
	
	public Verb() {
		
	}
	
	public Verb(Long id, String name, Language language){
		this.id = id;
		this.name = name;
		this.language = language;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne
    @JoinColumn(name="language_id")
    private Language language;
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName){
		this.name = newName;
	}

	public Language getLanguage() {
		return this.language;
	}
	
	public void setLanguage(Language newLanguage){
		this.language = newLanguage;
	}
}
