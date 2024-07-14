package com.app.conjugation.model;

import jakarta.persistence.Id;

public class VerbDTO {
	
	public VerbDTO() {
		
	}
	
	public VerbDTO(Integer id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Id
	private Integer id;
	
	private String name;

	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

}
