package com.app.conjugation.model;


import java.util.Date;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class BatchDTO {
	
	public BatchDTO() {
		
	}
	
	public BatchDTO(Integer id, Integer dayNumber, Date reviewingDate, List<TableDTO> tableList){
		this.id = id;
		this.setDayNumber(dayNumber);
		this.setReviewingDate(reviewingDate);
		this.setTableList(tableList);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Integer dayNumber;
	
	private Date reviewingDate;
	
	private List<TableDTO> tableList;
	
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

	public List<TableDTO> getTableList() {
		return tableList;
	}

	public void setTableList(List<TableDTO> tableList) {
		this.tableList = tableList;
	}

	
	
}
