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
            
            
            
            // Add the table list to the batch

            // Group by Table(tense and verb) within each batch
//            Map<TableDTO, List<Conjugation>> tableMap = batchConjugationList.stream().collect(Collectors
//            		.groupingBy(bc -> new TableDTO(
//            				new TenseDTO(bc.getConjugation().getTense().getId(), bc.getConjugation().getTense().getName()), 
//            				new VerbDTO(bc.getConjugation().getVerb().getId(), bc.getConjugation().getVerb().getName()), 
//            				new ArrayList<ConjugationDTO>()),
//            				Collectors.mapping(BatchConjugation::getConjugation, Collectors.toList())));


//          Collectors.mapping(bc -> new ConjugationDTO(
//          		bc.getConjugation().getId(),
//          		bc.getConjugation().getLabel(),
//          		bc.getConjugation().getPronoun().getName(),
//          		bc.getConjugation().getTense().getName(),
//          		bc.getConjugation().getVerb().getName()),
//          		Collectors.toList()
            
//            List<TableDTO> tableList = new ArrayList<TableDTO>();	
            
//            for (Map.Entry<TableDTO, List<ConjugationDTO>> tableEntry : tableMap.entrySet()) {
//            	
//            	 TableDTO table = tableEntry.getKey();
//                 List<ConjugationDTO> conjugations = tableEntry.getValue();
//                 table.setConjugationList(conjugations);
//                 tableList.add(table);
//                 
//            }
            
            BatchDTO batchDTO = new BatchDTO();
            batchDTO.setDayNumber(batch.getDayNumber());
            batchDTO.setReviewingDate(batch.getReviewingDate());
            batchDTO.setTableList(tableList);

            batchDTOList.add(batchDTO);
            
        }
        
		return batchDTOList;
		
	}
	
	
//	public List<BatchDTO> getByUserAndLanguage(Integer languageId){
//		
//		List<BatchConjugation> batchConjugationList = batchConjugationRepository.findByUserAndLanguage(languageId);
//		
//		List<BatchDTO> batchList = new ArrayList<BatchDTO>();
//		
//		Integer currentBatchId = null;
//		BatchDTO currentBatch = new BatchDTO();
//		List<TableDTO> currentTableList = new ArrayList<TableDTO>();
//		
//		for(BatchConjugation batchConjugation: batchConjugationList) {
//			
//			Integer batchId = batchConjugation.getId();
//			
//			// Current iteration is new batch
//			if (!currentBatchId.equals(batchId)) {
//				
//				// Adding current Batch to batch list
//				if(!currentTableList.isEmpty()) {
//					addBatch(currentBatch, currentTableList, batchList);
//					// Reset table list
//					currentTableList = new ArrayList<TableDTO>();
//				}
//				
//				// New batch
//				currentBatchId = batchId;
//				currentBatch = createBatch(batchConjugation);
//				
//			}
//			
//			currentTableList.add(new TableDTO())
//			
//		}
//		
//		return batchList;
//		
//	}
//	
//	private BatchDTO createBatch(BatchConjugation batchConjugation) {
//		BatchDTO batch = new BatchDTO();
//		batch.setId(batchConjugation.getBatch().getId());
//		batch.setReviewingDate(batchConjugation.getBatch().getReviewingDate());
//		batch.setDayNumber(batchConjugation.getBatch().getDayNumber());
//        return batch;
//    }
//	
//	private void addBatch(BatchDTO batch, List<TableDTO> tableList, List<BatchDTO> batchList) {
//        batch.setTableList(tableList);
//        batchList.add(batch);
//    }
	
}
