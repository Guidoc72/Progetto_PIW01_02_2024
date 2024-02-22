package it.akt.services;

import java.util.List;

import javax.management.loading.ClassLoaderRepository;

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
	
	public AulaService(AulaRepository aulaRepository, UtenteRepository utenteRepository,
			QuizRepository quizRepository) {
		this.aulaRepository = aulaRepository;
		this.utenteRepository = utenteRepository;
		this.quizRepository = quizRepository;
	}
	
/** 
Aggiunge un'aula al database. 
@param aula Aula object
@return aula Aula object
*/
	public Aula addAula(Aula aula) {
		return aulaRepository.save(aula);
	}

/** 
Modifica un'aula del database.
@param aula Aula object
@return aula Aula object
*/	
	public Aula updateAula(Aula aula) {
		return aulaRepository.save(aula);
	}

/**
* Trova un'aula del database tramite il suo id.
* @param Long id
* @return aula Aula object
*/
	public Aula getAulaById(Long id) {
		return aulaRepository.findById(id).orElseThrow(() -> 
			new RuntimeException(String.format("Non esiste nessuna classe con id: %d",id)));
	}

/**
 * Trova tutte le aule del database.
 * @return List aula object
 */
	public List<Aula> getAllAule() {
		return aulaRepository.findAll();
	}

/**
 * Elimina un'aula dal database tramite il suo id.
 * @param id aula object
 */
	public void deleteAulaById(Long id) {
	      aulaRepository.deleteById(id);
	}
	
/**
 * Restituisce la lista di studenti dell'aula nel database
 * @return Lista utente Utente object
 */
	public List<Utente> getAllUtentiInAula() {
		return aulaRepository.findAll();
	}
	
/**
 * Restituisce un utente tramite il suo id.
 * @param id aula object
 * @param id utente object
 * @return utente Utente object
 */
	public Utente getUtenteByIdInAula(Long aula_id, Long utente_id) {
		 return aulaRepository.getUtenteByIdInAula(aula_id, utente_id);
	 }
}