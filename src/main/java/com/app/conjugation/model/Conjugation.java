package com.app.conjugation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "conjugation")
public class Conjugation {
	
	public Conjugation() {
		
	}
	
	public Conjugation(Integer id, String label, Tense tense, Verb verb, Pronoun pronoun, Language language){
		this.id = id;
		this.label = label;
		this.language = language;
		this.tense = tense;
		this.verb = verb;
		this.pronoun = pronoun;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String label;
	
	@ManyToOne
    @JoinColumn(name="tense_id")
    private Tense tense;
	
	@ManyToOne
    @JoinColumn(name="verb_id")
    private Verb verb;
	
	@ManyToOne
    @JoinColumn(name="pronoun_id")
    private Pronoun pronoun;
	
	@ManyToOne
    @JoinColumn(name="language_id")
    private Language language;
	
	public Integer getId() {
		return this.id;
	}
	
	public String getLabel() {
		return this.label;
	}

	public Tense getTense() {
		return this.tense;
	}
	
	public Verb getVerb() {
		return this.verb;
	}
	
	public Pronoun getPronoun() {
		return this.pronoun;
	}

	public Language getLanguage() {
		return this.language;
	}
	
}
