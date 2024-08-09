package com.app.conjugation.model;

public class UserLearningLanguageDTO {
	
	public UserLearningLanguageDTO() {
		
	}
	
	public UserLearningLanguageDTO(Integer userId, Integer learningLanguageId) {
		this.setUserId(userId);
		this.setLearningLanguageId(learningLanguageId);
	}

	private Integer userId;
	
	private Integer learningLanguageId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLearningLanguageId() {
		return learningLanguageId;
	}

	public void setLearningLanguageId(Integer learningLanguageId) {
		this.learningLanguageId = learningLanguageId;
	}
	
	
	
}
