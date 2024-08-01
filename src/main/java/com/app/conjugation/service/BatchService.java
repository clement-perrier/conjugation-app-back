package com.app.conjugation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.conjugation.model.Batch;
import com.app.conjugation.model.BatchConjugation;
import com.app.conjugation.model.BatchDTO;
import com.app.conjugation.model.Conjugation;
import com.app.conjugation.model.ConjugationDTO;
import com.app.conjugation.model.Pronoun;
import com.app.conjugation.model.PronounDTO;
import com.app.conjugation.model.TableDTO;
import com.app.conjugation.model.Tense;
import com.app.conjugation.model.TenseDTO;
import com.app.conjugation.model.Verb;
import com.app.conjugation.model.VerbDTO;
import com.app.conjugation.repository.BatchConjugationRepository;
import com.app.conjugation.repository.BatchRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BatchService {

	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private BatchConjugationRepository batchConjugationRepository;
	
	@Autowired
	private TableService tableService;
	
	public List<Batch> getAll() {
		return batchRepository.findAll();
	}
	
	public List<BatchDTO> getByUserAndLanguage(Integer learningLanguageId){
		
		List<Batch> batchList = batchRepository.findByUserAndLanguage(learningLanguageId);
		
		List<BatchDTO> batchDTOList = new ArrayList<BatchDTO>();
		
		for(Batch batch : batchList) {
			
			// Tranform batch conjugation list into conjugation list
			List<Conjugation> conjugationList = batch.getBatchConjugationList().stream()
		            .map(BatchConjugation::getConjugation)
		            .collect(Collectors.toList());
			
			// Call table.service.convert with the conjugation list as the parameter => we have our conjugation per table => TableDTO list
            List<TableDTO> tableList = tableService.getTableListFromConjugationList(conjugationList);
     
            BatchDTO batchDTO = new BatchDTO();
            batchDTO.setId(batch.getId());
            batchDTO.setDayNumber(batch.getDayNumber());
            batchDTO.setReviewingDate(batch.getReviewingDate());
//            batchDTO.setLanguage(batch.getLanguage());
            batchDTO.setTableList(tableList);

            batchDTOList.add(batchDTO);
			
		}
		
		return batchDTOList;
		
	}
	
	public Integer saveBatch(BatchDTO batchDTO) {
		
		// Inserting Batch record
		Batch newBatch = mapBatchDTOToEntity(batchDTO);
		batchRepository.save(newBatch);
		
		// Inserting Batch Conjugation records
		for(TableDTO table : batchDTO.getTableList()) {
			
			for(ConjugationDTO conjugationDTO : table.getConjugationList()) {
				
				// Create and set BatchConjugation entity
                BatchConjugation newBatchConjugation = new BatchConjugation();
                newBatchConjugation.setBatch(newBatch);

                // Map ConjugationDTO to Conjugation entity
                Conjugation newConjugation = mapConjugationDTOToEntity(conjugationDTO, table);

                // Set the conjugation entity to BatchConjugation
                newBatchConjugation.setConjugation(newConjugation);

                // Save BatchConjugation record
                batchConjugationRepository.save(newBatchConjugation);
				
			}
			
		}
		
		return newBatch.getId();
		
	}
	
	private Batch mapBatchDTOToEntity(BatchDTO batchDTO) {
        Batch batch = new Batch();
        batch.setDayNumber(batchDTO.getDayNumber());
        batch.setReviewingDate(batchDTO.getReviewingDate());
//        batch.setLanguage(batchDTO.getLanguage());
        return batch;
    }

	private Conjugation mapConjugationDTOToEntity(ConjugationDTO conjugationDTO, TableDTO table) {
        Conjugation conjugation = new Conjugation();
        conjugation.setId(conjugationDTO.getId());
        conjugation.setLabel(conjugationDTO.getName());
        conjugation.setPronoun(mapPronounDTOToEntity(conjugationDTO.getPronoun()));
        conjugation.setVerb(mapVerbDTOToEntity(table.getVerb()));
        conjugation.setTense(mapTenseDTOToEntity(table.getTense()));
        return conjugation;
    }
	  
	private Pronoun mapPronounDTOToEntity(PronounDTO pronounDTO) {
        Pronoun pronoun = new Pronoun();
        pronoun.setName(pronounDTO.getName());
        pronoun.setOrder(pronounDTO.getOrder());
        return pronoun;
    }
	
	private Verb mapVerbDTOToEntity(VerbDTO verbDTO) {
        Verb verb = new Verb();
        verb.setName(verbDTO.getName());
        return verb;
    }

    private Tense mapTenseDTOToEntity(TenseDTO tenseDTO) {
        Tense tense = new Tense();
        tense.setName(tenseDTO.getName());
        return tense;
    }
    
}
