package com.app.conjugation.model;


import java.util.List;

import jakarta.persistence.Id;

public class UserDTO {
	
	public UserDTO() {
		
	}
	
	public UserDTO(Integer id, String firstname, String lastname, Integer lastSelectedLearningLanguage, List<LearningLanguage> learningLanguageList){
		this.id = id;
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setDefaultLearningLanguageId(lastSelectedLearningLanguage);
		this.setLearningLanguageList(learningLanguageList);
	}
		
	@Id
	private Integer id;
	
	private String firstname;
	
	private String lastname;	
	
	private Integer defaultLearningLanguageId;
	
	private List<LearningLanguage> learningLanguageList;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Integer getDefaultLearningLanguageId() {
		return defaultLearningLanguageId;
	}

	public void setDefaultLearningLanguageId(Integer defaultLearningLanguageId) {
		this.defaultLearningLanguageId = defaultLearningLanguageId;
	}

	public List<LearningLanguage> getLearningLanguageList() {
		return learningLanguageList;
	}

	public void setLearningLanguageList(List<LearningLanguage> learningLanguageList) {
		this.learningLanguageList = learningLanguageList;
	}
	
}
