package com.app.conjugation.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.conjugation.model.Batch;
import com.app.conjugation.model.BatchConjugation;
import com.app.conjugation.model.BatchDTO;
import com.app.conjugation.model.Conjugation;
import com.app.conjugation.model.TableDTO;
import com.app.conjugation.repository.BatchConjugationRepository;
import com.app.conjugation.repository.BatchRepository;

@Service
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
	
	public List<BatchDTO> getByUserAndLanguage(Integer languageId){
		
		List<BatchConjugation> batchConjugationListRepo = batchConjugationRepository.findByUserAndLanguage(languageId);
		
		// Group by batch
        Map<Batch, List<BatchConjugation>> batchMap = batchConjugationListRepo.stream().collect(Collectors.groupingBy(BatchConjugation::getBatch));
        
        List<Map.Entry<Batch, List<BatchConjugation>>> sortedBatchEntries = batchMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Batch::getReviewingDate)))
                .collect(Collectors.toList());

        List<BatchDTO> batchDTOList = new ArrayList<BatchDTO>();
        
        for (Map.Entry<Batch, List<BatchConjugation>> entry : sortedBatchEntries) {
        	
        	Batch batch = entry.getKey();
            List<BatchConjugation> batchConjugationList = entry.getValue();
            
            List<Conjugation> conjugationList = new ArrayList<Conjugation>();
            
            // Convert batchConjugationList into ConjugationList 
            for (BatchConjugation bc : batchConjugationList) {
            	conjugationList.add(new Conjugation(
	            			bc.getConjugation().getId(),
		              		bc.getConjugation().getLabel(),
		              		bc.getConjugation().getTense(),
		              		bc.getConjugation().getVerb(),
		              		bc.getConjugation().getPronoun(),
		              		bc.getConjugation().getLanguage()
            			));
            }
            
            // Call table.service.convert with the conjugation list as the parameter => we have our conjugation per table => TableDTO list
            List<TableDTO> tableList = tableService.getTableListFromConjugationList(conjugationList);
     
            BatchDTO batchDTO = new BatchDTO();
            batchDTO.setId(batch.getId());
            batchDTO.setDayNumber(batch.getDayNumber());
            batchDTO.setReviewingDate(batch.getReviewingDate());
            batchDTO.setTableList(tableList);

            batchDTOList.add(batchDTO);
            
        }
        
		return batchDTOList;
		
	}
	
}
