package com.app.conjugation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.conjugation.model.LearningLanguage;
import com.app.conjugation.repository.LearningLanguageRepository;

@Service
public class LearningLanguageService {

	@Autowired
	private LearningLanguageRepository learningLanguageRepository;
	
	public List<LearningLanguage> getAll(){
		return learningLanguageRepository.findAll();
	}
	
}
