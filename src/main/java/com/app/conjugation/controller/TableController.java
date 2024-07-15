package com.app.conjugation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.conjugation.model.TableDTO;
import com.app.conjugation.service.TableService;

@RestController
public class TableController {

	@Autowired
	private TableService tableService;
	
	@GetMapping("/tables")
	public List<TableDTO> getByLanguageGroupByTable(@RequestParam Integer languageId) {
		return tableService.getByLanguageGroupByTable(languageId);
	}

}
