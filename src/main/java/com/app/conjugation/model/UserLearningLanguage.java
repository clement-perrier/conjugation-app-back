package com.app.conjugation.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

	@Setter
    @Getter
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Integer id;

	@Setter
    @Getter
    @ManyToOne
    @JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name="learning_language_id")
	private LearningLanguage learningLanguage;

	@OneToMany(mappedBy = "userLearningLanguage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Batch> batchList;

}
