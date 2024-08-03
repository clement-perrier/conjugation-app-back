package com.app.conjugation.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_learning_language")
public class UserLearningLanguage {
	
	public UserLearningLanguage() {
		
	}
	
	public UserLearningLanguage(Integer id, User user, LearningLanguage learningLanguage) {
		this.setId(id);
		this.setUser(user);
		this.setLearningLanguage(learningLanguage);
	}

	@Id
	private Integer id;

	@ManyToOne
    @JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
    @ManyToOne
    @JoinColumn(name="learning_language_id")
	private LearningLanguage learningLanguage;	
	
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LearningLanguage getLearningLanguage() {
		return learningLanguage;
	}

	public void setLearningLanguage(LearningLanguage learningLanguage) {
		this.learningLanguage = learningLanguage;
	}
	
}
