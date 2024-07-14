package com.app.conjugation.model;

import jakarta.persistence.Id;

public class TenseDTO {
	
	public TenseDTO() {
		
	}
	
	public TenseDTO(Integer id, String name){
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
