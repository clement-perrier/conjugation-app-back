package com.app.conjugation.model;

import jakarta.persistence.Id;

public class VerbDTO {
	
	public VerbDTO() {
		
	}
	
	public VerbDTO(Long id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Id
	private Long id;
	
	private String name;

	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

}
