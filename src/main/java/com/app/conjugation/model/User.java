package com.app.conjugation.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	public User() {
		
	}
	
	public User(Integer id, String firstname, String lastname, List<UserLearningLanguage> userLearningLanguageList){
		this.id = id;
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setUserLearningLanguageList(userLearningLanguageList);
	}
	
	@Id
	private Integer id;
	
	private String firstname;
	
	private String lastname;	
	
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

	public List<UserLearningLanguage> getUserLearningLanguageList() {
		return userLearningLanguageList;
	}

	public void setUserLearningLanguageList(List<UserLearningLanguage> userLearningLanguageList) {
		this.userLearningLanguageList = userLearningLanguageList;
	}
	
}
