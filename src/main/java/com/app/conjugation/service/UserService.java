package com.app.conjugation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.conjugation.model.User;
import com.app.conjugation.model.UserDTO;
import com.app.conjugation.model.UserLearningLanguage;
import com.app.conjugation.model.LearningLanguage;
import com.app.conjugation.repository.LearningLanguageRepository;
import com.app.conjugation.repository.UserLearningLanguageRepository;
import com.app.conjugation.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LearningLanguageRepository learningLanguageRepository;
	
	@Autowired
	private UserLearningLanguageRepository userLearningLanguageRepository;
	
	public UserDTO getById(Integer id) {
		User user = userRepository.findById(id).get();
		return mapUserToDTO(user);
	}
	
	public UserDTO updateUserLearningLanguageList(Integer userId, Integer newLearningLanguageId) {
		
		// Retrieving the user we want to update
		User user = userRepository.findById(userId).get();
		// Retrieving the language we want to add to the user learning language list
		LearningLanguage learningLanguage = learningLanguageRepository.findById(newLearningLanguageId).get();
		
		// Creating the new UserLearningLanguage object with the user and learning language infos
		UserLearningLanguage newUserLearningLanguage = new UserLearningLanguage();
		newUserLearningLanguage.setUser(user);
		newUserLearningLanguage.setLearningLanguage(learningLanguage);
		
		// Adding the user learning language record in the db
		userLearningLanguageRepository.save(newUserLearningLanguage);
		
		// Returning the updated User as UserDTO
		return mapUserToDTO(user);		
	}
	
	public UserDTO updateDefaultLearningLanguage(Integer userId, Integer newLearningLanguageId) {
		
		// Retrieving the user we want to update
		User user = userRepository.findById(userId).get();
		// Retrieving the language we want to add to the user learning language list
		LearningLanguage learningLanguage = learningLanguageRepository.findById(newLearningLanguageId).get();
		
		user.setDefaultLearningLanguage(learningLanguage);
		
		// Adding the user learning language record in the db
		userRepository.save(user);
		
		// Returning the updated User as UserDTO
		return mapUserToDTO(user);		
	}
	
 	private UserDTO mapUserToDTO(User user) {
 		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFullname(user.getFullName());
		userDTO.setEmail(user.getEmail());
		userDTO.setDefaultLearningLanguage(user.getDefaultLearningLanguage());
		
		List<LearningLanguage> learningLanguageList = new ArrayList<LearningLanguage>();
		
		for(UserLearningLanguage userLearningLanguage : user.getUserLearningLanguageList()) {
			learningLanguageList.add(userLearningLanguage.getLearningLanguage());
		}
		
		userDTO.setLearningLanguageList(learningLanguageList);
		
		return userDTO;
		
	}
 	
 	private User mapUserDTOtoEntity(UserDTO userDTO) {
 		
 		User user = new User();
 		user.setId(userDTO.getId());
 		user.setFullName(userDTO.getFullname());
 		user.setEmail(userDTO.getEmail());
 		user.setDefaultLearningLanguage(userDTO.getDefaultLearningLanguage());
 		
		List<UserLearningLanguage> userLearningLanguageList = new ArrayList<UserLearningLanguage>();
		
		for(LearningLanguage learningLanguage : userDTO.getLearningLanguageList()) {
			UserLearningLanguage userLearningLanguage = new UserLearningLanguage();
			userLearningLanguage.setUser(user);
			userLearningLanguage.setLearningLanguage(learningLanguage);
			userLearningLanguageList.add(userLearningLanguage);
		}
		
		user.setUserLearningLanguageList(userLearningLanguageList);
 		
 		return user;
 	}
	
}
