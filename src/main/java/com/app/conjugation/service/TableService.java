package com.app.conjugation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.conjugation.model.Conjugation;
import com.app.conjugation.model.ConjugationDTO;
import com.app.conjugation.model.TableDTO;
import com.app.conjugation.repository.ConjugationRepository;

@Service
public class TableService {

	@Autowired
	private ConjugationRepository conjugationRepository;
	
	public List<TableDTO> getByLanguageGroupByTable(Integer languageId) {
		
        // Flat list of all conjugations for one language
        List<Conjugation> conjugationList = conjugationRepository.findByLanguageId(languageId);
    	// Result => list of tables
        List<TableDTO> tableList = new ArrayList<>();

        if (conjugationList.isEmpty()) {
            return tableList;
        }

        TableDTO currentTable = new TableDTO();
        List<ConjugationDTO> currentConjugationList = new ArrayList<>();
        Integer currentTenseId = null;
        Integer currentVerbId = null;

        for (Conjugation conjugation : conjugationList) {
            Integer tenseId = conjugation.getTense().getId();
            Integer verbId = conjugation.getVerb().getId();

            // In current iteration is new table
            if (!tenseId.equals(currentTenseId) || !verbId.equals(currentVerbId)) {
                if (!currentConjugationList.isEmpty()) {
            		// Adding the previous table to the result
                    addTable(currentTable, currentConjugationList, tableList);
                    // Reset currentConjugationList
                    currentConjugationList = new ArrayList<>();
                }
                currentTenseId = tenseId;
                currentVerbId = verbId;
                // Creating new currentTable because iteration in new table
                currentTable = createTable(conjugation);
            }
        	// Adding the iterated conjugation to the current conjugation list
            currentConjugationList.add(new ConjugationDTO(conjugation.getId(), conjugation.getLabel(), conjugation.getPronoun().getName()));
        }
        
        // Add the last table
        if (!currentConjugationList.isEmpty()) {
            addTable(currentTable, currentConjugationList, tableList);
        }

        return tableList;
    }

    private TableDTO createTable(Conjugation conjugation) {
        TableDTO table = new TableDTO();
        table.setTenseName(conjugation.getTense().getName());
        table.setVerbName(conjugation.getVerb().getName());
        return table;
    }

    private void addTable(TableDTO table, List<ConjugationDTO> conjugationList, List<TableDTO> tableList) {
        table.setConjugationList(conjugationList);
        tableList.add(table);
    }

//	private void addConjugation(List<Conjugation> conjugationList, 
//								List<ConjugationDTO> currentConjugationList, 
//								Integer i) {
//		currentConjugationList.add(new ConjugationDTO(conjugationList.get(i).getId(), conjugationList.get(i).getLabel()));
//	}
//	
//	private void addTable(List<Conjugation> conjugationList, 
//						  List<ConjugationDTO> currentConjugationList,
//						  List<TableDTO> tableList,
//						  TableDTO currentTable,
//						  Integer i) {
//		currentTable.setTenseName(conjugationList.get(i).getTense().getName());
//		currentTable.setVerbName(conjugationList.get(i).getVerb().getName());
//		currentTable.setConjugationList(currentConjugationList);
//		tableList.add(currentTable);
//	}
//	
//	public List<TableDTO> getByLanguageGroupByTable(Integer languageId){
//		
//		List<TableDTO> tableList = new ArrayList<TableDTO>();
//		List<Conjugation> conjugationList = conjugationRepository.findByLanguageId(languageId);
//		TableDTO currentTable = new TableDTO();
//		List<ConjugationDTO> currentConjugationList = new ArrayList<ConjugationDTO>();
//		
//		for(int i = 0; i < conjugationList.size(); i++) {
//			
//			if(i+1 < conjugationList.size()) {
//				if(conjugationList.get(i).getTense().getId() == conjugationList.get(i+1).getTense().getId() 
//						&& conjugationList.get(i).getVerb().getId() == conjugationList.get(i+1).getVerb().getId()) {
//					addConjugation(conjugationList, currentConjugationList, i);
//				} else {
//					addConjugation(conjugationList, currentConjugationList, i);
//					addTable(conjugationList, currentConjugationList, tableList, currentTable, i);
//					currentTable = new TableDTO();
//					currentConjugationList = new ArrayList<ConjugationDTO>();
//				}
//			} else {
//				addConjugation(conjugationList, currentConjugationList, i);
//				addTable(conjugationList, currentConjugationList, tableList, currentTable, i);
//				currentTable = new TableDTO();
//				currentConjugationList = new ArrayList<ConjugationDTO>();
//			}
//			
//		}
//		
//		return tableList;
//		
//	}
	
}
