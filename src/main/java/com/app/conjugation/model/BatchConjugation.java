package com.app.conjugation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "batch_conjugation")
public class BatchConjugation {
	
	public BatchConjugation() {
		
	}
	
	public BatchConjugation(Integer id, Batch batch, Conjugation conjugation){
		this.id = id;
		this.setBatch(batch);
		this.setConjugation(conjugation);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
    @JoinColumn(name="batch_id")
	private Batch batch;
	
	@ManyToOne
    @JoinColumn(name="conjugation_id")
	private Conjugation conjugation;
	
	public Integer getId() {
		return this.id;
	}
	
	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Conjugation getConjugation() {
		return conjugation;
	}

	public void setConjugation(Conjugation conjugation) {
		this.conjugation = conjugation;
	}

	
	
	
}
