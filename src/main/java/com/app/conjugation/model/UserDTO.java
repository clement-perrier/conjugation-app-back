package com.app.conjugation.model;


import java.util.List;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

	private Boolean isGuest;

}
