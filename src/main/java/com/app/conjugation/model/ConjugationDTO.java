package com.app.conjugation.model;

public class ConjugationDTO {
	
	public ConjugationDTO() {
		
	}
	
	public ConjugationDTO(Integer id, String name, PronounDTO pronoun, String tenseName, String verbName, Boolean correct){
		this.id = id;
		this.name = name;
		this.pronoun = pronoun;
		this.tenseName = tenseName;
		this.verbName = verbName;
		this.setCorrect(correct);
	}
	
	private Integer id;
	
	private String name;
	
	private PronounDTO pronoun;
	
	private String tenseName;
	
	private String verbName;
	
	private Boolean correct;

	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public PronounDTO getPronoun() {
		return this.pronoun;
	}
	
	public String getTenseName() {
		return this.tenseName;
	}
	
	public String getVerbName() {
		return this.verbName;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

}
