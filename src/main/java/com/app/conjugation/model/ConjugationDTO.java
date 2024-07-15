package com.app.conjugation.model;

import jakarta.persistence.Id;

public class ConjugationDTO {
	
	public ConjugationDTO() {
		
	}
	
	public ConjugationDTO(Integer id, String name, String pronounName){
		this.id = id;
		this.name = name;
		this.pronounName = pronounName;
	}
	
	@Id
	private Integer id;
	
	private String name;
	
	private String pronounName;

	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPronounName() {
		return this.pronounName;
	}

}
