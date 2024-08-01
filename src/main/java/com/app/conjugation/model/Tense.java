package com.app.conjugation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tense")
public class Tense {
	
	public Tense() {
		
	}
	
	public Tense(Integer id, String name, LearningLanguage language){
		this.id = id;
		this.name = name;
		this.language = language;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@ManyToOne
    @JoinColumn(name="language_id")
    private LearningLanguage language;
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName){
		this.name = newName;
	}

	public LearningLanguage getLanguage() {
		return this.language;
	}
	
}
