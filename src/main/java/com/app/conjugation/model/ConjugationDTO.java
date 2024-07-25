package com.app.conjugation.model;

public class ConjugationDTO {
	
	public ConjugationDTO() {
		
	}
	
	public ConjugationDTO(Integer id, String name, String pronounName, String tenseName, String verbName){
		this.id = id;
		this.name = name;
		this.pronounName = pronounName;
		this.tenseName = tenseName;
		this.verbName = verbName;
	}
	
	private Integer id;
	
	private String name;
	
	private String pronounName;
	
	private String tenseName;
	
	private String verbName;

	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPronounName() {
		return this.pronounName;
	}
	
	public String getTenseName() {
		return this.tenseName;
	}
	
	public String getVerbName() {
		return this.verbName;
	}

}
