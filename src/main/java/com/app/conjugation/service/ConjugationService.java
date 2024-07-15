package com.app.conjugation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.conjugation.model.Conjugation;
import com.app.conjugation.repository.ConjugationRepository;

@Service
public class ConjugationService {

	@Autowired
	private ConjugationRepository conjugationRepository;
	
	public Conjugation getById(Integer id) {
		return conjugationRepository.findById(id).get();
	}
	
	public List<Conjugation> getByLanguage(Integer id) {
		return conjugationRepository.findByLanguageId(id);
	}
	
	public List<Conjugation> getAll(){
		return conjugationRepository.findAll();
	}
	
}
