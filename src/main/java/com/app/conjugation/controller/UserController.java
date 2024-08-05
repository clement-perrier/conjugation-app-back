package com.app.conjugation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
}