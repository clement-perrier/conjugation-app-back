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
			+ "join UserLearningLanguage ull on b.userLearningLanguage.id = ull.id "
			+ "where ull.user.id = :userId and ull.learningLanguage.id = :languageId")
	List<Batch> findByUserAndLanguage(@Param("userId") Integer userId, @Param("languageId") Integer languageId);
	
	@Query("select b "
			+ "from Batch b "
			+ "where b.reviewingDate <= current_date "
			+ "and "
			+ "b.reviewingDate in "
			+ "(SELECT min(b2.reviewingDate) "
			+ "FROM Batch b2 "
			+ "WHERE b2.reviewingDate <= current_date "
			+ "GROUP BY b2.userLearningLanguage)")
	List<Batch> findDueBatches();

	@Query(value = """
        SELECT DISTINCT u.email as userEmail, u.device_token AS deviceToken,
                        ll.name AS languageName
        FROM user u
        JOIN user_learning_language ull ON ull.user_id = u.id
        JOIN learning_language ll ON ull.learning_language_id = ll.id
        JOIN batch b ON b.user_learning_language_id = ull.id
        WHERE b.reviewing_date <= CURRENT_DATE
          AND b.reviewing_date IN (
              SELECT MIN(b2.reviewing_date)
              FROM batch b2
              WHERE b2.reviewing_date <= CURRENT_DATE
              GROUP BY b2.user_learning_language_id
          )
        """, nativeQuery = true)
	List<UserLanguageInfo> findUserDeviceTokenAndLanguageNameForDueBatches();

	public static interface UserLanguageInfo {
		String getUserEmail();
		String getDeviceToken();
		String getLanguageName();
	}

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
