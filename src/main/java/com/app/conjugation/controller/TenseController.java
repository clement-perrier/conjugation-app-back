package com.app.conjugation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.conjugation.model.Tense;
import com.app.conjugation.service.TenseService;

@RestController
@RequestMapping("/tense")
public class TenseController {

	@Autowired
	private TenseService tenseService;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/{id}")
	public ResponseEntity<Tense> getById(@PathVariable Long id) {
		Tense tense = tenseService.getById(id);
		if(tense != null) {
			return new ResponseEntity<>(tense, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/list/language-id/{languageId}")
	public List<Tense> getByLanguage(@PathVariable Long languageId) {
		return tenseService.getByLanguage(languageId);
	}

	@GetMapping("/list/all")
	public List<Tense> getAll() {
		List<Tense> tenseList = tenseService.getAll();
		return tenseList;
	}

}
