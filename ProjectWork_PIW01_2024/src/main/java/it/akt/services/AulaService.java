package it.akt.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import it.akt.models.Utente;
import it.akt.models.Aula;
import it.akt.models.Quiz;
import it.akt.repositories.AulaRepository;
import it.akt.repositories.UtenteRepository;
import it.akt.repositories.QuizRepository;

@Service
public class AulaService {

	private AulaRepository aulaRepository;
	private UtenteRepository utenteRepository;
	private QuizRepository quizRepository;
	
	public AulaService(AulaRepository aulaRepository, 
			UtenteRepository utenteRepository,
			QuizRepository quizRepository) {
		this.aulaRepository = aulaRepository;
		this.utenteRepository = utenteRepository;
		this.quizRepository = quizRepository;
	}
	
/**
 * Aggiunge un oggetto aula al db.
 * @param	aula Aula object
 * @return  aula Aula object
 */
	public Aula addAula(Aula aula) {
		return aulaRepository.save(aula);
	}

/**
 * Aggiorna l'oggetto aula nel db.
 * @param	aula Aula object
 * @return  aula Aula object
 */
	public Aula updateAula(Aula aula) {
		return aulaRepository.save(aula);
	}

/**
 * Trova e restituisce un oggetto aula nel db tramite l'id.
 * @param	id aula Aula
 * @return  aula Aula object
 * @throws  RuntimeException se non trova nessun oggetto con l'id richiesto.
 */
	public Aula getAulaById(Long id) {
		return aulaRepository.findById(id).orElseThrow(() -> 
			new RuntimeException(String.format("Non esiste nessuna classe con id: %d", id)));
	}
	
	
/**
 * Trova e restituisce tutti gli oggetti aula nel db.
 * @param	Set Aula 
 * @return  Set Aula 
 */
	public List<Aula> getAllAule() {
		return aulaRepository.findAll();
	}

/**
 * Elimina un oggetto aula dal db tramite il suo id.
 * @param	id aula Aula
 * @return 	(void)
 */
	public void deleteAulaById(Long id) {
	      aulaRepository.deleteById(id);
	}
	
/**
 * Restituisce la lista di oggetti studente di una determinata tabella del db.
 * @param	id aula Aula
 * @return  List Utente
 */
	public List<Utente> findUtenteByAulaId(Long id) {
		if (id == null) {
			return utenteRepository.findByAuleIdIsNull();
		} else {
			return utenteRepository.findByAuleId(id);
		}
	}
	
/**
 * Restituisce la lista di oggetti quiz di una determinata tabella del db.
 * @param	id aula Aula
 * @return  Set Quiz 
 */
	public Set<Quiz> findQuizByAulaId(Long id) {
		return quizRepository.findByAuleId(id);
	}
	
/**
 * Restituisce un oggetto Utente in 
 * @param id
 * @return
 */
	public Aula findByUtentiId(Long id) {
		return aulaRepository.findByUtentiId(id);
	}
		
}