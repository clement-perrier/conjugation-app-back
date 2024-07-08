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
import com.app.conjugation.model.TenseDTO;

@Repository
public interface TenseRepository extends JpaRepository<Tense, Long> {
	
	//	@Query("select t from Tense t where t.language.id = :id")
	@Query("select new com.app.conjugation.model.TenseDTO(t.id, t.name) from Tense t where t.language.id = :id")
    List<TenseDTO> findByLanguageId(@Param("id") Long id);

}
