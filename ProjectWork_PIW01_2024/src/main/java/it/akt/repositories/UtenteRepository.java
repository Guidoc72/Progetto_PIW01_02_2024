package it.akt.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.Utente;

@Repository
public interface UtenteRepository  extends ListCrudRepository<Utente, Long>{

	
	Utente findByEmail(String email);
	

	
	
	Utente findBypasswordToken(String passwordToken);


	List<Utente> findAllByRuolo(int ruolo);

	/**
	 * Restituisce la lista di oggetti utente.
	 * @return Lista utente Utente 
	 */
		List<Utente> findAll();

	/**
	 * Restituisce la lista di oggetti utente di una determinata tabella aula tramite il suo id.
	 * @param id aula Aula
	 * @return Lista utente Utente
	 */
		List<Utente> findByAuleId(Long id);

	/**
	 * Restituisce la lista di oggetti utente che non hanno un collegamento con nessuna tabella aula.
	 * @return Lista utente Utente
	 */
		List<Utente> findByAuleIdIsNull();
}


