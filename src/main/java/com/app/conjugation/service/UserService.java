package com.app.conjugation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.conjugation.model.User;
import com.app.conjugation.model.UserDTO;
import com.app.conjugation.model.UserLearningLanguage;
import com.app.conjugation.model.LearningLanguage;
import com.app.conjugation.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserDTO getById(Integer id) {
		User user = userRepository.findById(id).get();
		return mapUserToDTO(user);
	}
	
	private UserDTO mapUserToDTO(User user) {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstname(user.getFirstname());
		userDTO.setLastname(user.getLastname());
		userDTO.setDefaultLearningLanguageId(user.getDefaultLearningLanguage().getId());
		
		List<LearningLanguage> learningLanguageList = new ArrayList<LearningLanguage>();
		
		for(UserLearningLanguage userLearningLanguage : user.getUserLearningLanguageList()) {
			learningLanguageList.add(userLearningLanguage.getLearningLanguage());
		}
		
		userDTO.setLearningLanguageList(learningLanguageList);
		
		return userDTO;
		
	}
	
}
