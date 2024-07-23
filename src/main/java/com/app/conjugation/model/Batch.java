package com.app.conjugation.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "batch")
public class Batch {
	
	public Batch() {
		
	}
	
	public Batch(Integer id, Integer dayNumber, Date reviewingDate){
		this.id = id;
		this.setDayNumber(dayNumber);
		this.setReviewingDate(reviewingDate);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Integer dayNumber;
	
	private Date reviewingDate;
	
	public Integer getId() {
		return this.id;
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
	
}
