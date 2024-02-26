package it.akt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.Aula;
import it.akt.models.Utente;

@Repository
public interface AulaRepository extends ListCrudRepository<Aula, Long> {

/**
 * Restituisce la lista degli utenti presenti nell'aula
 * @param id aula Aula object
 * @return Lista utente Utente 
 */
	@Query("SELECT DISTINCT a FROM aula a JOIN FETCH a.utente WHERE a.id = :aulaId")
	public List<Utente> getAllUtentiInAula(Long aulaId); 

/**
 * Restituisce un utente della classe tramite il suo id
 * @param id aula Aula object
 * @param id utente Utente object
 * @return utente Utente object
 */
	@Query("SELECT DISTINC a FROM aula a JOIN FETCH a.utente WHERE a.id = :utenteId ")
	public Utente getUtenteByIdInAula(Long aulaId, Long utenteId);
	
}