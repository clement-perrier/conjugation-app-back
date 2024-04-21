package com.app.conjugation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.app.conjugation.model.Tense;
import com.app.conjugation.repository.TenseRepository;

import jakarta.annotation.Resource;

@Service
public class TenseService {

	@Autowired
	private TenseRepository tenseRepository;
	
	public Tense getById(Long id) {
		return tenseRepository.findById(id).get();
	}
	
	public List<Tense> getByLanguage(Long id) {
		return tenseRepository.findByLanguageId(id);
	}
	
	public List<Tense> getAll(){
		return tenseRepository.findAll();
	}
	
}
