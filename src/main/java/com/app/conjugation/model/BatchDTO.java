package com.app.conjugation.model;

import java.util.Date;
import java.util.List;

public class BatchDTO {
	
	public BatchDTO() {
		
	}
	
	public BatchDTO(Integer id, Integer dayNumber, Date reviewingDate, List<TableDTO> tableList, UserLearningLanguageDTO userLearningLanguageDTO){
		this.setId(id);
		this.setDayNumber(dayNumber);
		this.setReviewingDate(reviewingDate);
		this.setTableList(tableList);
		this.setUserLearningLanguage(userLearningLanguageDTO);
	}
	
	private Integer id;
	
	private Integer dayNumber;
	
	private Date reviewingDate;
	
	private UserLearningLanguageDTO userLearningLanguageDTO;
	
	private List<TableDTO> tableList;
	
	public Integer getId() {
        return id;
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

	public List<TableDTO> getTableList() {
		return tableList;
	}

	public void setTableList(List<TableDTO> tableList) {
		this.tableList = tableList;
	}

	public UserLearningLanguageDTO getUserLearningLanguage() {
		return userLearningLanguageDTO;
	}

	public void setUserLearningLanguage(UserLearningLanguageDTO userLearningLanguageDTO) {
		this.userLearningLanguageDTO = userLearningLanguageDTO;
	}
	

//	public Language getLanguage() {
//		return language;
//	}
//
//	public void setLanguage(Language language) {
//		this.language = language;
//	}
	
}
