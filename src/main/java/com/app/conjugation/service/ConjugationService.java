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
public class ConjugationService {

	@Autowired
	private ConjugationRepository conjugationRepository;
	
	public Conjugation getById(Integer id) {
		return conjugationRepository.findById(id).get();
	}
	
	public List<Conjugation> getByLanguage(Integer id) {
		return conjugationRepository.findByLanguageId(id);
	}
	
	public List<Conjugation> getAll(){
		return conjugationRepository.findAll();
	}
	
	private void addConjugation(List<Conjugation> conjugationList, 
								List<ConjugationDTO> currentConjugationList, 
								Integer i) {
		currentConjugationList.add(new ConjugationDTO(conjugationList.get(i).getId(), conjugationList.get(i).getLabel()));
	}
	
	private void addTable(List<Conjugation> conjugationList, 
						  List<ConjugationDTO> currentConjugationList,
						  List<TableDTO> tableList,
						  TableDTO currentTable,
						  Integer i) {
		currentTable.setTenseName(conjugationList.get(i).getTense().getName());
		currentTable.setVerbName(conjugationList.get(i).getVerb().getName());
		currentTable.setConjugationList(currentConjugationList);
		tableList.add(currentTable);
	}
	
	public List<TableDTO> getByLanguageGroupByTable(Integer languageId){
		
		List<TableDTO> tableList = new ArrayList<TableDTO>();
		List<Conjugation> conjugationList = conjugationRepository.findByLanguageId(languageId);
		TableDTO currentTable = new TableDTO();
		List<ConjugationDTO> currentConjugationList = new ArrayList<ConjugationDTO>();
		
		for(int i = 0; i < conjugationList.size(); i++) {
			
			if(i+1 < conjugationList.size()) {
				if(conjugationList.get(i).getTense().getId() == conjugationList.get(i+1).getTense().getId() 
						&& conjugationList.get(i).getVerb().getId() == conjugationList.get(i+1).getVerb().getId()) {
					addConjugation(conjugationList, currentConjugationList, i);
				} else {
					addConjugation(conjugationList, currentConjugationList, i);
					addTable(conjugationList, currentConjugationList, tableList, currentTable, i);
					currentTable = new TableDTO();
					currentConjugationList = new ArrayList<ConjugationDTO>();
				}
			} else {
				addConjugation(conjugationList, currentConjugationList, i);
				addTable(conjugationList, currentConjugationList, tableList, currentTable, i);
				currentTable = new TableDTO();
				currentConjugationList = new ArrayList<ConjugationDTO>();
			}
			
		}
		
		return tableList;
		
	}
	
}
