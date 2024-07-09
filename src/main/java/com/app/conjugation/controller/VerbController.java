package com.app.conjugation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.conjugation.model.Verb;
import com.app.conjugation.model.VerbDTO;
import com.app.conjugation.service.VerbService;

@RestController
public class VerbController {

	@Autowired
	private VerbService verbService;

	@GetMapping("/verb")
	public ResponseEntity<Verb> getById(@PathVariable Long id) {
		Verb verb = verbService.getById(id);
		if(verb != null) {
			return new ResponseEntity<>(verb, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/verbs")
	public List<VerbDTO> getByLanguage(@RequestParam Long languageId) {
		return verbService.getByLanguage(languageId);
	}

	@GetMapping("/verbs/all")
	public List<Verb> getAll() {
		List<Verb> verbList = verbService.getAll();
		return verbList;
	}

}
