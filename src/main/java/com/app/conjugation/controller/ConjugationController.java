package com.app.conjugation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.conjugation.model.Conjugation;
import com.app.conjugation.model.TableDTO;
import com.app.conjugation.service.ConjugationService;

@RestController
public class ConjugationController {

	@Autowired
	private ConjugationService conjugationService;
	
	@GetMapping("/conjugations/byLanguage")
	public List<Conjugation> getByLanguage(@RequestParam Integer languageId) {
		return conjugationService.getByLanguage(languageId);
	}
	
	@GetMapping("/conjugations/all")
	public List<Conjugation> getAll() {
		return conjugationService.getAll();
	}
	
	@GetMapping("/conjugations/table")
	public List<TableDTO> getByLanguageGroupByTable(@RequestParam Integer languageId) {
		return conjugationService.getByLanguageGroupByTable(languageId);
	}

}
