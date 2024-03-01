package it.akt.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.Utente;

@Repository
public interface UtenteRepository  extends ListCrudRepository<Utente, Long>{
	
	Utente findByEmail(String email);
	
	List<Utente> findAllByRuolo(int ruolo);

}


