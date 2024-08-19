package com.app.conjugation.model;



import java.util.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "batch")
public class Batch {
	
	public Batch() {
		
	}
	
	public Batch(Integer id, Integer dayNumber, Date reviewingDate, UserLearningLanguage userLearningLanguage){
		this.id = id;
		this.setDayNumber(dayNumber);
		this.setReviewingDate(reviewingDate);
		this.setUserLearningLanguage(userLearningLanguage);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer dayNumber;
	
	@Temporal(TemporalType.DATE)
	private Date reviewingDate;
	
	@ManyToOne
    @JoinColumn(name="user_learning_language_id")
	private UserLearningLanguage userLearningLanguage;

	@OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BatchConjugation> batchConjugationList;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}

	public Date getReviewingDate() {
		return reviewingDate;
	}

	public void setReviewingDate(Date reviewingDate) {
		this.reviewingDate = reviewingDate;
	}
	
	
	public UserLearningLanguage getUserLearningLanguage() {
		return userLearningLanguage;
	}

	public void setUserLearningLanguage(UserLearningLanguage userLearningLanguage) {
		this.userLearningLanguage = userLearningLanguage;
	}

	public List<BatchConjugation> getBatchConjugationList() {
		return batchConjugationList;
	}

	public void setBatchConjugationList(List<BatchConjugation> batchConjugationList) {
		this.batchConjugationList = batchConjugationList;
	}
	
}
