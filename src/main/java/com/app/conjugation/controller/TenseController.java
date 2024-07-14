package com.app.conjugation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.conjugation.model.Tense;
import com.app.conjugation.model.TenseDTO;
import com.app.conjugation.service.TenseService;

@RestController
public class TenseController {

	@Autowired
	private TenseService tenseService;

	@GetMapping("/tense")
	public ResponseEntity<Tense> getById(@RequestParam Integer id) {
		Tense tense = tenseService.getById(id);
		if(tense != null) {
			return new ResponseEntity<>(tense, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/tenses")
	public List<TenseDTO> getByLanguage(@RequestParam Integer languageId) {
		return tenseService.getByLanguage(languageId);
	}

	@GetMapping("/tenses/all")
	public List<Tense> getAll() {
		List<Tense> tenseList = tenseService.getAll();
		return tenseList;
	}

}
