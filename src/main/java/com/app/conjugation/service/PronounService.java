package com.app.conjugation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.conjugation.model.Pronoun;
import com.app.conjugation.model.PronounDTO;
import com.app.conjugation.repository.PronounRepository;

@Service
public class PronounService {

	@Autowired
	private PronounRepository pronounRepository;
	
	public Pronoun getById(Long id) {
		return pronounRepository.findById(id).get();
	}
	
	public List<PronounDTO> getByLanguage(Long id) {
		return pronounRepository.findByLanguageId(id);
	}
	
	public List<Pronoun> getAll(){
		return pronounRepository.findAll();
	}
	
}
