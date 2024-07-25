package com.app.conjugation.model;

public class VerbDTO {
	
	public VerbDTO() {
		
	}
	
	public VerbDTO(Integer id, String name){
		this.id = id;
		this.name = name;
	}
	
	private Integer id;
	
	private String name;

	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

}
