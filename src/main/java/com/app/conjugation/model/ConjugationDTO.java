package com.app.conjugation.model;

public class ConjugationDTO {
	
	public ConjugationDTO() {
		
	}
	
	public ConjugationDTO(Integer id, String name, PronounDTO pronoun, String tenseName, String verbName){
		this.id = id;
		this.name = name;
		this.pronoun = pronoun;
		this.tenseName = tenseName;
		this.verbName = verbName;
	}
	
	private Integer id;
	
	private String name;
	
	private PronounDTO pronoun;
	
	private String tenseName;
	
	private String verbName;

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

}
