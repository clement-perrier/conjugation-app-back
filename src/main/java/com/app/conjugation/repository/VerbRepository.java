package com.app.conjugation.repository;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import com.app.conjugation.model.Tense;
import com.app.conjugation.model.Verb;

@Repository
public interface VerbRepository extends JpaRepository<Verb, Long> {
	
	@Query("select v from Verb v where v.language.id = :id")
    List<Verb> findByLanguageId(@Param("id") Long id);

}
