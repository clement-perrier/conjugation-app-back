package com.app.conjugation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.conjugation.model.PronounDTO;
import com.app.conjugation.service.PronounService;

@RestController
public class PronounController {

	@Autowired
	private PronounService pronounService;
	
	@GetMapping("/pronouns")
	public List<PronounDTO> getByLanguage(@RequestParam Integer languageId) {
		return pronounService.getByLanguage(languageId);
	}

}
