package com.app.conjugation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.conjugation.model.Batch;
import com.app.conjugation.model.BatchDTO;
import com.app.conjugation.model.VerbDTO;
import com.app.conjugation.service.BatchService;

@RestController
public class BatchController {

	@Autowired
	private BatchService batchService;
	
	@GetMapping("/batchs/all")
	public List<Batch> getAll() {
		return batchService.getAll();
	}
	
	@GetMapping("/batchs/byUserAndLanguage")
	public List<BatchDTO> getByUserAndLanguage(@RequestParam Integer languageId) {
		return batchService.getByUserAndLanguage(languageId);
	}


}
