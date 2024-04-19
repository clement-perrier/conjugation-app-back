package com.app.conjugation.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;

import com.app.conjugation.ConjugationApplication;
import com.app.conjugation.model.Tense;

import jakarta.annotation.Resource;

@SpringBootTest(classes = ConjugationApplication.class)
class TenseRepositoryTest {

	  @Resource 
	  private TenseRepository tenseRepository;
	 
	  @Test void test() { 
		  
		  Tense tense = new Tense(); 
		  tense.setName("Test"); Tense
		  savedTense = tenseRepository.save(tense);
		  
		  // Vérifier si l'enregistrement a réussi assertThat(savedTense).isNotNull();
		  assertThat(savedTense.getId()).isNotNull(); 
		  // Vérifier que l'identifiant généré est non nul 
		  assertThat(savedTense.getName()).isEqualTo("Test"); 
		  
	 }
	 
}
