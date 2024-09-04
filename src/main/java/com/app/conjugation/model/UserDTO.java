package com.app.conjugation.model;


import java.util.List;

import jakarta.persistence.Id;

public class UserDTO {
	
	public UserDTO() {
		
	}
	
	public UserDTO(Integer id, String email, String deviceToken, LearningLanguage defaultLearningLanguage, List<LearningLanguage> learningLanguageList){
		this.id = id;
		this.setEmail(email);
		this.setDeviceToken(deviceToken);
		this.setDefaultLearningLanguage(defaultLearningLanguage);
		this.setLearningLanguageList(learningLanguageList);
	}
		
	@Id
	private Integer id;
	
	private String email;
	
	private String deviceToken;
	
	private LearningLanguage defaultLearningLanguage;
	
	private List<LearningLanguage> learningLanguageList;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String fullname) {
		this.deviceToken = fullname;
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
