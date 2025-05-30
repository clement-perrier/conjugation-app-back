package com.app.conjugation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.conjugation.exceptions.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.app.conjugation.model.User;
import com.app.conjugation.model.UserDTO;
import com.app.conjugation.model.UserLearningLanguage;
import com.app.conjugation.model.LearningLanguage;
import com.app.conjugation.repository.LearningLanguageRepository;
import com.app.conjugation.repository.UserLearningLanguageRepository;
import com.app.conjugation.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LearningLanguageRepository learningLanguageRepository;
	
	@Autowired
	private UserLearningLanguageRepository userLearningLanguageRepository;
	
	public UserDTO getById(Integer id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			return mapUserToDTO(user);
		} else {
			throw new EntityNotFoundException("User not found with id: " + id);
		}
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
	
	public UserDTO updateDeviceToken(Integer userId, String deviceToken) {

		// Retrieving the user we want to update
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			// Setting new device token
			user.setDeviceToken(deviceToken);
			// Updating the user in the DB
			userRepository.save(user);
			// Returning the updated User as UserDTO
			return mapUserToDTO(user);
		} else {
			// Handle the case when the user is not found, e.g., throw an exception or return null
			throw new UserNotFoundException("User with ID " + userId + " not found.");
		}

	}
	
	public void removeUserDeviceToken(Integer userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.get();
		user.setDeviceToken(null);
		userRepository.save(user);
		System.out.println("Device token from user removed");
	}
	
 	private UserDTO mapUserToDTO(User user) {
 		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setDefaultLearningLanguage(user.getDefaultLearningLanguage());
		userDTO.setDeviceToken(user.getDeviceToken());
		userDTO.setIsGuest(user.isGuest());
		
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
 	
 	public ResponseEntity<String> deleteUser(Integer userId) {
//		Optional<User> optionalUser = userRepository.findById(userId);
//		if (optionalUser.isPresent()) {
//			User user = optionalUser.get();
//			userLearningLanguageRepository.deleteByUser(user);
//			List<UserLearningLanguage> userLearningLanguageList = userLearningLanguageRepository.findByUser(user);
//			userLearningLanguageRepository.deleteAllById();
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
//		}
		userRepository.deleteById(userId);
		return ResponseEntity.ok("User deleted successfully");
	}
	
}
