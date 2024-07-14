package com.app.conjugation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pronoun")
public class Pronoun {
	
	public Pronoun() {
		
	}
	
	public Pronoun(Integer id, String name, int order, Language language){
		this.id = id;
		this.name = name;
		this.language = language;
		this.order = order;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private int order;
	
	@ManyToOne
    @JoinColumn(name="language_id")
    private Language language;
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getOrder() {
		return this.order;
	}

	public Language getLanguage() {
		return this.language;
	}
}
