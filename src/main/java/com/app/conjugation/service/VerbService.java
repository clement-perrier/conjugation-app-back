package com.app.conjugation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.conjugation.model.Verb;
import com.app.conjugation.model.VerbDTO;
import com.app.conjugation.repository.VerbRepository;


@Service
public class VerbService {

	@Autowired
	private VerbRepository verbRepository;
	
	public Verb getById(Integer id) {
		return verbRepository.findById(id).get();
	}
	
	public List<VerbDTO> getByLanguage(Integer id) {
		return verbRepository.findByLanguageId(id);
	}
	
	public List<Verb> getAll(){
		return verbRepository.findAll();
	}
	
}
