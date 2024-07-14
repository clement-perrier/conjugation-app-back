package com.app.conjugation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.conjugation.model.Verb;
import com.app.conjugation.model.VerbDTO;

@Repository
public interface VerbRepository extends JpaRepository<Verb, Integer> {
	
	@Query("select new com.app.conjugation.model.VerbDTO(v.id, v.name) from Verb v where v.language.id = :id")
    List<VerbDTO> findByLanguageId(@Param("id") Integer id);

}
