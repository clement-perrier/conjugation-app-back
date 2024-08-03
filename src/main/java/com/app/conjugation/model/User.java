package com.app.conjugation.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	public User() {
		
	}
	
	public User(Integer id, String firstname, String lastname, LearningLanguage defaultLearningLanguage, List<UserLearningLanguage> userLearningLanguageList){
		this.id = id;
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setDefaultLearningLanguage(defaultLearningLanguage);
		this.setUserLearningLanguageList(userLearningLanguageList);
	}
	
	@Id
	private Integer id;
	
	private String firstname;
	
	private String lastname;
	
	@ManyToOne
    @JoinColumn(name="default_learning_language_id")
	private LearningLanguage defaultLearningLanguage;
	
	@OneToMany(mappedBy = "user")
	private List<UserLearningLanguage> userLearningLanguageList;
	
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

	public void setDefaultLearningLanguage(LearningLanguage lastSelectedLearningLanguage) {
		this.defaultLearningLanguage = lastSelectedLearningLanguage;
	}

	public List<UserLearningLanguage> getUserLearningLanguageList() {
		return userLearningLanguageList;
	}

	public void setUserLearningLanguageList(List<UserLearningLanguage> userLearningLanguageList) {
		this.userLearningLanguageList = userLearningLanguageList;
	}
	
}
