package com.app.conjugation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.conjugation.model.Conjugation;

@Repository
public interface ConjugationRepository extends JpaRepository<Conjugation, Integer> {

	@Query("select c "
			+ "from Conjugation c "
			+ "where c.language.id = :languageId "
			+ "ORDER BY c.tense.id, c.verb.id, c.pronoun.id")
	List<Conjugation> findByLanguageId(@Param("languageId") Integer languageId);
	
}
