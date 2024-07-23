package com.app.conjugation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.conjugation.model.BatchConjugation;

@Repository
public interface BatchConjugationRepository extends JpaRepository<BatchConjugation, Integer> {

	@Query("select bc "
			+ "from BatchConjugation bc")
	List<BatchConjugation> findAll();
	
	@Query("select bc "
			+ "from BatchConjugation bc "
			+ "where bc.conjugation.language.id = :languageId "
			+ "ORDER BY bc.batch.reviewingDate, bc.batch.dayNumber")
	List<BatchConjugation> findByUserAndLanguage(@Param("languageId") Integer languageId);
	
//	@Query(value = "SELECT b.reviewing_date AS reviewingDate, b.day_number AS dayNumber, bc.batch_id AS batchId, "
//            + "JSON_ARRAYAGG(JSON_OBJECT('id', c.id, 'label', c.label, 'pronoun', p.name)) AS conjugationList "
//            + "FROM conjugation c "
//            + "JOIN batch_conjugation bc ON c.id = bc.conjugation_id "
//            + "JOIN batch b ON bc.batch_id = b.id "
//            + "JOIN pronoun p ON c.pronoun_id = p.id "
//            + "GROUP BY bc.batch_id, b.reviewing_date, b.day_number "
//            + "ORDER BY b.reviewing_date, b.day_number", nativeQuery = true)
//	List<Object> findByUserAndLanguage();
	
}
