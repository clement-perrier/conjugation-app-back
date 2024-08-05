package com.app.conjugation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.conjugation.model.LearningLanguage;
import com.app.conjugation.service.LearningLanguageService;

@RestController
public class LearningLanguageController {

	@Autowired
	private LearningLanguageService learningLanguageService;

	@GetMapping("/learningLanguages")
	public List<LearningLanguage> getAll() {
		List<LearningLanguage> learningLanguageList = learningLanguageService.getAll();
		return learningLanguageList;
	}

}
