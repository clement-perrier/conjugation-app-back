package com.app.conjugation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.conjugation.model.Tense;

@Repository
public interface TenseRepository extends JpaRepository<Tense, Long> {

}
