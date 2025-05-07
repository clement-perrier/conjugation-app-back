package com.app.conjugation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.app.conjugation.repository.ConjugationRepository;
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
import com.app.conjugation.model.UserLearningLanguage;
import com.app.conjugation.model.Verb;
import com.app.conjugation.model.VerbDTO;
import com.app.conjugation.repository.BatchConjugationRepository;
import com.app.conjugation.repository.BatchRepository;
import com.app.conjugation.repository.UserLearningLanguageRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import javax.swing.text.html.Option;

@Service
@Transactional
public class BatchService {

	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private BatchConjugationRepository batchConjugationRepository;
	
	@Autowired
	private UserLearningLanguageRepository userLearningLanguageRepository;

	@Autowired
	private ConjugationRepository conjugationRepository;

	@Autowired
	private TableService tableService;
	
	public List<Batch> getAll() {
		return batchRepository.findAll();
	}
	
	public List<BatchDTO> getByUserAndLanguage(Integer userId, Integer learningLanguageId){
		
		List<Batch> batchList = batchRepository.findByUserAndLanguage(userId, learningLanguageId);
		
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
            batchDTO.setReviewingDate(batch.getReviewingDate());;
            batchDTO.setTableList(tableList);

            batchDTOList.add(batchDTO);
			
		}
		
		return batchDTOList;
		
	}
	
	@Transactional
	public BatchDTO saveBatch(BatchDTO batchDTO) {
		
		// Inserting Batch record
		Batch newBatch = mapBatchDTOToEntity(batchDTO);
		newBatch.setBatchConjugationList(new ArrayList<>());

		// Inserting Batch Conjugation records
		for(TableDTO table : batchDTO.getTableList()) {

			for(ConjugationDTO conjugationDTO : table.getConjugationList()) {

                // Map ConjugationDTO to Conjugation entity
				// Conjugation newConjugation = mapConjugationDTOToEntity(conjugationDTO, table);
				Conjugation conjugation = conjugationRepository.findById(conjugationDTO.getId())
						.orElseThrow(() -> new RuntimeException("Conjugation not found"));

				// Create BatchConjugation and set relationships
				BatchConjugation batchConjugation = new BatchConjugation();
				batchConjugation.setBatch(newBatch);
				batchConjugation.setConjugation(conjugation);

				newBatch.getBatchConjugationList().add(batchConjugation);

				//  batchConjugationRepository.save(newBatchConjugation);

			}

		}

		batchRepository.save(newBatch);

		batchDTO.setId(newBatch.getId());
		
		return batchDTO;
		
	}
	
	@Transactional
	public Integer updateBatch(BatchDTO batchDTO) {

	    // Fetch existing Batch record
		Batch batch = batchRepository.findById(batchDTO.getId()).orElseThrow();

	    // Update attributes of the existing Batch entity
	    batch.setDayNumber(batchDTO.getDayNumber());
	    batch.setReviewingDate(batchDTO.getReviewingDate());
		// Getting new BatchConjugation list from updated batch
		List<BatchConjugation> newBatchConjugationList = getBatchConjugationListFromBatchDTO(batchDTO);
		// Updating batch BatchConjugation list so JPA is aware
		batch.getBatchConjugationList().clear();
		batch.getBatchConjugationList().addAll(newBatchConjugationList);

	    // Save updated Batch record
	    batchRepository.save(batch);

	    return batch.getId();
	}

	public Integer deleteBatch(Integer batchId) {
		Optional<Batch> optionalBatch = batchRepository.findById(batchId);
        optionalBatch.ifPresent(batch -> batchRepository.delete(batch));
		return batchId;
	}

	public List<BatchConjugation> getBatchConjugationListFromBatchDTO(BatchDTO batchDTO) {
		Integer batchId = batchDTO.getId();


		List<BatchConjugation> batchConjugationList = new ArrayList<BatchConjugation>();
		List<TableDTO> tableList = batchDTO.getTableList();
		for (TableDTO table: tableList) {
			List<ConjugationDTO> conjugationList = table.getConjugationList();
			for (ConjugationDTO conjugation: conjugationList) {
				Optional<BatchConjugation> optionalBatchConjugation = batchConjugationRepository.findByBatch_IdAndConjugation_Id(batchId, conjugation.getId());
				optionalBatchConjugation.ifPresent(batchConjugationList::add);
			}
		}

		return batchDTO.getTableList().stream()
				.flatMap(table -> table.getConjugationList().stream())
				.map(conjugationDTO ->
						batchConjugationRepository.findByBatch_IdAndConjugation_Id(batchId, conjugationDTO.getId()))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}
	
	private Batch mapBatchDTOToEntity(BatchDTO batchDTO) {
		
        Batch batch = new Batch();
		if (batchDTO.getId() != null) { batch.setId(batchDTO.getId()); }
        batch.setDayNumber(batchDTO.getDayNumber());
        batch.setReviewingDate(batchDTO.getReviewingDate());
        
        Integer userId = batchDTO.getUserLearningLanguage().getUserId();
		Integer learningLanguageId = batchDTO.getUserLearningLanguage().getLearningLanguageId();
		UserLearningLanguage userLearningLanguage = userLearningLanguageRepository.findByUserIdAndLearningLanguageId(userId, learningLanguageId);
        batch.setUserLearningLanguage(userLearningLanguage);

//		List<BatchConjugation> batchConjugationList = getBatchConjugationListFromBatchDTO(batchDTO);
//		batch.setBatchConjugationList(batchConjugationList);
        
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
	
	private UserLearningLanguage mapUserLearningLanguageIdToEntity(Integer id) {
		UserLearningLanguage userLearningLanguage = new UserLearningLanguage();
		userLearningLanguage.setUser(null);
		return userLearningLanguage;
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
