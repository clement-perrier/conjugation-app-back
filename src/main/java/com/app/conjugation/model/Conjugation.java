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
	
	public Conjugation(Integer id, String label, Tense tense, Verb verb, Pronoun pronoun){
		this.id = id;
		this.label = label;
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
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public Tense getTense() {
		return this.tense;
	}
	
	public void setTense(Tense tense) {
		this.tense = tense;
	}
	
	public Verb getVerb() {
		return this.verb;
	}
	
	public void setVerb(Verb verb) {
		this.verb = verb;
	}
	
	public Pronoun getPronoun() {
		return this.pronoun;
	}
	
	public void setPronoun(Pronoun pronoun) {
		this.pronoun = pronoun;
	}
	
}
