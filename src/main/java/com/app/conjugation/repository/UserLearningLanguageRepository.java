package com.app.conjugation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.conjugation.model.UserLearningLanguage;

@Repository
public interface UserLearningLanguageRepository extends JpaRepository<UserLearningLanguage, Integer> {

	@Query("select ull "
			+ "from UserLearningLanguage ull "
			+ "where ull.user.id = :userId "
			+ "and ull.learningLanguage.id = :learningLanguageId")
	UserLearningLanguage findByUserIdAndLearningLanguageId(@Param("userId") Integer UserId, @Param("learningLanguageId") Integer learningLanguageId);

}
