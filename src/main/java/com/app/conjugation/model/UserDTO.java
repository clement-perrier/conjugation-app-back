package com.app.conjugation.model;


import java.util.List;

import jakarta.persistence.Id;

public class UserDTO {
	
	public UserDTO() {
		
	}
	
	public UserDTO(Integer id, String firstname, String lastname, LearningLanguage defaultLearningLanguage, List<LearningLanguage> learningLanguageList){
		this.id = id;
		this.setFullname(firstname);
		this.setEmail(lastname);
		this.setDefaultLearningLanguage(defaultLearningLanguage);
		this.setLearningLanguageList(learningLanguageList);
	}
		
	@Id
	private Integer id;
	
	private String fullname;
	
	private String email;	
	
	private LearningLanguage defaultLearningLanguage;
	
	private List<LearningLanguage> learningLanguageList;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
