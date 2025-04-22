package com.app.conjugation.model;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.List;

public class BatchDTO {
	
	public BatchDTO() {}
	
	public BatchDTO(Integer id, Integer dayNumber, Instant reviewingDate, List<TableDTO> tableList, UserLearningLanguageDTO userLearningLanguageDTO){
		this.setId(id);
		this.setDayNumber(dayNumber);
		this.setReviewingDate(reviewingDate);
		this.setTableList(tableList);
		this.setUserLearningLanguage(userLearningLanguageDTO);
	}

	@Setter
    @Getter
    private Integer id;
	
	@Setter
    @Getter
    private Integer dayNumber;
	
	@Setter
    @Getter
    private Instant reviewingDate;
	
	private UserLearningLanguageDTO userLearningLanguageDTO;
	
	@Setter
    @Getter
    private List<TableDTO> tableList;

    public UserLearningLanguageDTO getUserLearningLanguage() {
		return userLearningLanguageDTO;
	}

	public void setUserLearningLanguage(UserLearningLanguageDTO userLearningLanguageDTO) {
		this.userLearningLanguageDTO = userLearningLanguageDTO;
	}

}
