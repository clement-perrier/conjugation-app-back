package com.app.conjugation.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "batch")
public class Batch {
	
	public Batch() {}

	public Batch(Integer id, Integer dayNumber, LocalDate reviewingDate, UserLearningLanguage userLearningLanguage){
		this.id = id;
		this.setDayNumber(dayNumber);
		this.setReviewingDate(reviewingDate);
		this.setUserLearningLanguage(userLearningLanguage);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer dayNumber;
	
//	@Temporal(TemporalType.DATE)
	private LocalDate reviewingDate;
	
	@ManyToOne
    @JoinColumn(name="user_learning_language_id")
	private UserLearningLanguage userLearningLanguage;

	@OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BatchConjugation> batchConjugationList;


    public User getUser() {
		return userLearningLanguage.getUser();
	}
	
}
