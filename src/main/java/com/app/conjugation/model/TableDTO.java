package com.app.conjugation.model;

import java.util.List;

public class TableDTO {
	
	public TableDTO() {
		
	}
	
	public TableDTO(TenseDTO tense, VerbDTO verb, List<ConjugationDTO> conjugationList) {
		this.tense = tense;
		this.verb = verb;
		this.conjugationList = conjugationList;
	}

	private TenseDTO tense;
	
	private VerbDTO verb;
	
	private List<ConjugationDTO> conjugationList;

	public TenseDTO getTense() {
		return tense;
	}

	public void setTense(TenseDTO tense) {
		this.tense = tense;
	}

	public VerbDTO getVerb() {
		return verb;
	}

	public void setVerb(VerbDTO verb) {
		this.verb = verb;
	}
	
	public List<ConjugationDTO> getConjugationList() {
		return conjugationList;
	}

	public void setConjugationList(List<ConjugationDTO> conjugationList) {
		this.conjugationList = conjugationList;
	}
	
}
