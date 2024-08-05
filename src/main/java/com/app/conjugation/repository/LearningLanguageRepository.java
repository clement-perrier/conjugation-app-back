package com.app.conjugation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.conjugation.model.LearningLanguage;

@Repository
public interface LearningLanguageRepository extends JpaRepository<LearningLanguage, Integer> {


}
