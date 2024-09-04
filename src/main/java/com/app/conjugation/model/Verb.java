package com.app.conjugation.model;

import jakarta.persistence.Column;
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
	
	public Verb(Integer id, String name, LearningLanguage language){
		this.id = id;
		this.name = name;
		this.language = language;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name = "usage_rank", unique = true)
	private Integer rank;
	
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLanguage(LearningLanguage language) {
		this.language = language;
	}
	
	
	
}
