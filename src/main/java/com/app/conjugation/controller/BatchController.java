package com.app.conjugation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.conjugation.model.Batch;
import com.app.conjugation.model.BatchDTO;
import com.app.conjugation.service.BatchService;

@RestController
public class BatchController {

	@Autowired
	private BatchService batchService;
	
	@GetMapping("/batchs/all")
	public List<Batch> getAll() {
		return batchService.getAll();
	}
	
	@GetMapping("/batchesByUserAndLanguage")
	public List<BatchDTO> getByUserAndLanguage(@RequestParam Integer userId, @RequestParam Integer languageId) {
		return batchService.getByUserAndLanguage(userId, languageId);
	}

	@PostMapping("/newBatch")
    public ResponseEntity<Integer> createTense(@RequestBody BatchDTO batch) {
        Integer savedBatchId = batchService.saveBatch(batch);
        return ResponseEntity.ok(savedBatchId);
    }
	
	@PutMapping("/updateBatch")
	public ResponseEntity<Integer> updateBatch(@RequestBody BatchDTO batchDTO) {
	    Integer updatedBatchId = batchService.updateBatch(batchDTO);
	    return ResponseEntity.ok(updatedBatchId);
	}
	
	// Preflight request handling
    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handlePreflight() {
        return ResponseEntity.ok().build();
    }

}
