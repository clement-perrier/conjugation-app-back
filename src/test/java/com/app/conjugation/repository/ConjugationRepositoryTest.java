//package com.app.conjugation.repository;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.app.conjugation.ConjugationApplication;
//import com.app.conjugation.model.Conjugation;
//import com.app.conjugation.model.TableDTO;
//
//import jakarta.annotation.Resource;
//
//
//@SpringBootTest(classes = ConjugationApplication.class)
//class ConjugationRepositoryTest {
//
//    @Resource
//    private ConjugationRepository conjugationRepository;
//
//    @Test
//    void testFindByLanguageIdGroupByTable() {
//        Integer languageId = 1;
//        Integer tenseId = 1;
//        Integer verbId = 1;
//
//        // Use appropriate class based on your choice (Projection or DTO)
//        List<Conjugation> results = conjugationRepository.findByLanguageIdGroupByTable(languageId);
//
//        assertNotNull(results);
//        assertFalse(results.isEmpty());
//
//        for (Conjugation result : results) {
//            System.out.println("Tense: " + result.getTense().getName() + ", Verb: " + result.getVerb().getName() + ", Conjugation: " + result.getLabel());
//        }
//    }
//}
