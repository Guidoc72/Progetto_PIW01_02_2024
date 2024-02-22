package it.akt.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.Utente;

@Repository
public interface UtenteRepository  extends ListCrudRepository<Utente, Long>{

}


