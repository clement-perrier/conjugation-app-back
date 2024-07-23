package com.app.conjugation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.conjugation.model.Conjugation;
import com.app.conjugation.model.ConjugationDTO;
import com.app.conjugation.model.TableDTO;
import com.app.conjugation.model.TenseDTO;
import com.app.conjugation.model.VerbDTO;
import com.app.conjugation.repository.ConjugationRepository;

@Service
public class TableService {

	@Autowired
	private ConjugationRepository conjugationRepository;
	
	public List<TableDTO> getByLanguage(Integer languageId) {
		
        // Flat list of all conjugations for one language
        List<Conjugation> conjugationList = conjugationRepository.findByLanguageId(languageId);
        
        return getTableListFromConjugationList(conjugationList);
    	
    }
	
	public List<TableDTO> getTableListFromConjugationList(List<Conjugation> conjugationList) {
		
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

            // Current iteration is new table
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
            currentConjugationList.add(new ConjugationDTO(
            							conjugation.getId(), 
            							conjugation.getLabel(), 
            							conjugation.getPronoun().getName(),
            							conjugation.getTense().getName(),
            							conjugation.getVerb().getName()));
        }
        
        // Add the last table
        if (!currentConjugationList.isEmpty()) {
            addTable(currentTable, currentConjugationList, tableList);
        }

        return tableList;
		
	}

    private TableDTO createTable(Conjugation conjugation) {
        TableDTO table = new TableDTO();
        table.setTense(new TenseDTO(conjugation.getTense().getId(), conjugation.getTense().getName()));
        table.setVerb(new VerbDTO(conjugation.getVerb().getId(), conjugation.getVerb().getName()));
        return table;
    }

    private void addTable(TableDTO table, List<ConjugationDTO> conjugationList, List<TableDTO> tableList) {
        table.setConjugationList(conjugationList);
        tableList.add(table);
    }
	
}
