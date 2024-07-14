package com.app.conjugation.model;

import java.util.List;

public class TableDTO {
	
	public TableDTO() {
		
	}
	
	public TableDTO(String tenseName, String verbName, List<ConjugationDTO> conjugationList){
		this.setTenseName(tenseName);
		this.setVerbName(verbName);
		this.setConjugationList(conjugationList);
	}

	public String getTenseName() {
		return tenseName;
	}

	public void setTenseName(String tenseName) {
		this.tenseName = tenseName;
	}

	public String getVerbName() {
		return verbName;
	}

	public void setVerbName(String verbName) {
		this.verbName = verbName;
	}

	public List<ConjugationDTO> getConjugationList() {
		return conjugationList;
	}

	public void setConjugationList(List<ConjugationDTO> conjugationList) {
		this.conjugationList = conjugationList;
	}

	private String tenseName;
	
	private String verbName;
	
	private List<ConjugationDTO> conjugationList;
	
	
}
