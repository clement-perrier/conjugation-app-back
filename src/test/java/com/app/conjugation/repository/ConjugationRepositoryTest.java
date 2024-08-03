package com.app.conjugation.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import com.app.conjugation.ConjugationApplication;
import com.app.conjugation.model.BatchConjugation;
import com.app.conjugation.model.BatchDTO;
import com.app.conjugation.model.ConjugationDTO;
import com.app.conjugation.model.TableDTO;
import com.app.conjugation.service.BatchService;

import jakarta.annotation.Resource;


@SpringBootTest(classes = ConjugationApplication.class)
class ConjugationRepositoryTest {
    
    @Autowired
    private BatchService batchService;

    @Test
    void testGroupByBatch() {

        // Use appropriate class based on your choice (Projection or DTO)
//        List<BatchDTO> results = batchService.getByUserAndLanguage(1);
//
//        assertNotNull(results);
//        assertFalse(results.isEmpty());
//
//        for (BatchDTO result : results) {
//            System.out.println("Date: " + result.getReviewingDate() + ", Day: " + result.getDayNumber());
//            for (TableDTO table : result.getTableList()) {
//            	System.out.println("Tense: " + table.getTense().getName() + ", Verb: " + table.getVerb().getName());
//            	for(ConjugationDTO conjugation : table.getConjugationList()) {
//            		System.out.println("Conjugation: " + conjugation.getName());
//            	}
//            }
//        }
    }
}
