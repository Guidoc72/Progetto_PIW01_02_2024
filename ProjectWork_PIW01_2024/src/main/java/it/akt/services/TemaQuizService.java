package it.akt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.akt.models.Quiz;
import it.akt.models.TemaQuiz;
import it.akt.repositories.TemaQuizRepository;

/**
 * Classe di servizio per la gestione delle entità TemaQuiz.
 * @author Federico
 */
@Service
public class TemaQuizService {

/**
 * Repository per la gestione del tema dei quiz.
 */
    private TemaQuizRepository temaQuizRepository;

/**
 * Costruisce un nuovo TemaQuizService con il repository specificato.
 *
 * @param temaQuizRepository il repository per le entità TemaQuiz
 */
    @Autowired
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
}