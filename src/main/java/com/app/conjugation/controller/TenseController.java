package com.app.conjugation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	  @GetMapping("/list/all") public List<Tense> getAll() { 
		  List<Tense> tenseList= tenseService.getAll(); return tenseList; 
	  }
	 
		/*
		 * @GetMapping("/list/all") public String getAll() { String tenseList =
		 * tenseService.getAll(); return tenseList; }
		 */
}
