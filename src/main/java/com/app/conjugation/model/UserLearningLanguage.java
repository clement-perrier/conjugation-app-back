package com.app.conjugation.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserLearningLanguage {
	
	public UserLearningLanguage() {
		
	}
	
	public UserLearningLanguage(Integer id, User user, UserLearningLanguage userLearningLanguage) {
		this.setId(id);
		this.setUser(user);
		this.setUserLearningLanguage(userLearningLanguage);
	}

	@Id
	private Integer id;

	@ManyToOne
    @JoinColumn(name="user_id")
	private User user;
	
    @ManyToOne
    @JoinColumn(name="learning_language_id")
	private UserLearningLanguage userLearningLanguage;	
	
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

	public UserLearningLanguage getUserLearningLanguage() {
		return userLearningLanguage;
	}

	public void setUserLearningLanguage(UserLearningLanguage userLearningLanguage) {
		this.userLearningLanguage = userLearningLanguage;
	}
	
}
