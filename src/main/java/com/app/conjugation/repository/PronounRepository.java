package com.app.conjugation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.conjugation.model.Pronoun;
import com.app.conjugation.model.PronounDTO;

@Repository
public interface PronounRepository extends JpaRepository<Pronoun, Long> {
	
	//	@Query("select t from Tense t where t.language.id = :id")
	@Query("select new com.app.conjugation.model.PronounDTO(p.id, p.name, p.order) from Pronoun p where p.language.id = :id")
    List<PronounDTO> findByLanguageId(@Param("id") Long id);

}
