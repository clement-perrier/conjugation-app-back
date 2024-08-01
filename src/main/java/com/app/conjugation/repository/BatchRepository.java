package com.app.conjugation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.conjugation.model.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

	@Query("select b "
			+ "from Batch b")
	List<Batch> findAll();
	
	@Query("select b "
			+ "from Batch b "
			+ "where b.userLearningLanguage.id = :languageId")
	List<Batch> findByUserAndLanguage(@Param("languageId") Integer anguageId);
	
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
