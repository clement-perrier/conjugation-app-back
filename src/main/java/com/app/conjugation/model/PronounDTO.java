package com.app.conjugation.model;

public class PronounDTO {
	
	public PronounDTO() {
		
	}
	
	public PronounDTO(Integer id, String name, int order){
		this.id = id;
		this.name = name;
		this.order = order;
	}

	private Integer id;
	
	private String name;
	
	private int order;

	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getOrder() {
		return this.order;
	}

}
