package com.app.conjugation.model;


import java.util.List;

import jakarta.persistence.Id;

public class UserDTO {
	
	public UserDTO() {
		
	}
	
	public UserDTO(Integer id, String firstname, String lastname, LearningLanguage defaultLearningLanguage, List<LearningLanguage> learningLanguageList){
		this.id = id;
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setDefaultLearningLanguage(defaultLearningLanguage);
		this.setLearningLanguageList(learningLanguageList);
	}
		
	@Id
	private Integer id;
	
	private String firstname;
	
	private String lastname;	
	
	private LearningLanguage defaultLearningLanguage;
	
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
	
	public LearningLanguage getDefaultLearningLanguage() {
		return defaultLearningLanguage;
	}

	public void setDefaultLearningLanguage(LearningLanguage defaultLearningLanguage) {
		this.defaultLearningLanguage = defaultLearningLanguage;
	}

	public List<LearningLanguage> getLearningLanguageList() {
		return learningLanguageList;
	}

	public void setLearningLanguageList(List<LearningLanguage> learningLanguageList) {
		this.learningLanguageList = learningLanguageList;
	}
	
}
