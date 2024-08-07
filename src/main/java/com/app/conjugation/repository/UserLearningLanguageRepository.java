package com.app.conjugation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.conjugation.model.UserLearningLanguage;

@Repository
public interface UserLearningLanguageRepository extends JpaRepository<UserLearningLanguage, Integer> {


}
