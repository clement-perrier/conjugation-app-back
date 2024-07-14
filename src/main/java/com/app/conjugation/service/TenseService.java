package com.app.conjugation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.conjugation.model.Tense;
import com.app.conjugation.model.TenseDTO;
import com.app.conjugation.repository.TenseRepository;

@Service
public class TenseService {

	@Autowired
	private TenseRepository tenseRepository;
	
	public Tense getById(Integer id) {
		return tenseRepository.findById(id).get();
	}
	
	public List<TenseDTO> getByLanguage(Integer id) {
		return tenseRepository.findByLanguageId(id);
	}
	
	public List<Tense> getAll(){
		return tenseRepository.findAll();
	}
	
}
