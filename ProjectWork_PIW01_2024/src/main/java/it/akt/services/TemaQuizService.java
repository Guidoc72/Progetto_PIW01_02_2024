package it.akt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.akt.models.Quiz;
import it.akt.models.TemaQuiz;
import it.akt.repositories.TemaQuizRepository;

@Service
public class TemaQuizService {

    private TemaQuizRepository temaQuizRepository;

    public TemaQuizService(TemaQuizRepository temaQuizRepository) {
        this.temaQuizRepository = temaQuizRepository;
    }

/**
 * Crea e aggiunge un tema al database e restituisce un oggetto di tipo TemaQuiz
 * @param temaQuiz TemaQuiz object
 * @return temaQuiz TemaQuiz object
 */
    
    public TemaQuiz createTema(TemaQuiz temaQuiz) {
        return temaQuizRepository.save(temaQuiz);
    }

    /**
     * Restituisce una lista di tutti i temi
     * @return List temaQuiz object
     */
    public List<TemaQuiz> getAllTemi() {
    	return temaQuizRepository.findAll();
    }
    
/**
 * Modifica il tema nel database e restituisce l'oggetto
 * @param temaQuiz TemaQuiz object
 * @return temaQuiz TemaQuiz object
 */
    public TemaQuiz updateTema(TemaQuiz temaQuiz) {
        return temaQuizRepository.save(temaQuiz);
    }

/**
 * Trova un tema tramite il suo id
 * @param id temaQuiz object
 * @return temaQuiz TemaQuiz object
 */
    public TemaQuiz getTemaById(Long id) {
        return temaQuizRepository.findById(id).orElseThrow(() -> 
        		new RuntimeException(String.format("Non esiste nessun tema con id: %d", id)
        		));
    } 
/**
 * Elimina un tema tramite il suo id
 * @param id temaQuiz object
 */

    public void deleteTemaById(Long id) {
        temaQuizRepository.deleteById(id);
    }
    
    
    // 3. Associazione tra temi e quiz
//    @Transactional
//    public void associateQuizWithTema(Long temaId, List<Quiz> quizList) {
//        TemaQuiz temaQuiz = temaQuizRepository.findById(temaId)
//                .orElseThrow(() -> new IllegalArgumentException("Tema non trovato"));
//        temaQuiz.setQuizList(quizList);
//        temaQuizRepository.save(temaQuiz);
//    }

    // 6. Paginazione e ordinamento
//    public List<TemaQuiz> getPaginatedTemiQuiz(int page, int size) {
//        return temaQuizRepository.findAll(PageRequest.of(page, size)).getContent();
//    }
}