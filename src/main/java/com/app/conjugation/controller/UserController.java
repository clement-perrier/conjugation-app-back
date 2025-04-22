package com.app.conjugation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.conjugation.model.UserDTO;
import com.app.conjugation.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public UserDTO getById(@RequestParam Integer userId) {
		return userService.getById(userId);
	}
	
	@PutMapping("/updateUserLearningLanguageList")
	public ResponseEntity<UserDTO> updateUserLearningLanguageList(@RequestParam Integer userId, @RequestParam Integer learningLanguageId) {
		return ResponseEntity.ok(userService.updateUserLearningLanguageList(userId, learningLanguageId));
	}
	
	@PutMapping("/updateUserDefaultLearningLanguage")
	public ResponseEntity<UserDTO> updateDefaultLearningLanguage(@RequestParam Integer userId, @RequestParam Integer learningLanguageId) {
		return ResponseEntity.ok(userService.updateDefaultLearningLanguage(userId, learningLanguageId));
	}
	
	@PutMapping("/updateUserDeviceToken")
	public ResponseEntity<UserDTO> updateUserDeviceToken(@RequestParam Integer userId, @RequestParam String deviceToken) {
		return ResponseEntity.ok(userService.updateDeviceToken(userId, deviceToken));
	}

	@DeleteMapping("deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		return userService.deleteUser(id);
	}

}