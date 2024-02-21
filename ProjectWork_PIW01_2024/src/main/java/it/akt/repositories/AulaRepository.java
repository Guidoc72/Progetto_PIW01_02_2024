package it.akt.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.Aula;


@Repository
public interface AulaRepository extends ListCrudRepository<Aula, Long> {

	@Query("SELECT DISTINCT a FROM aula a JOIN FETCH a.utente WHERE a.id = :aula_id")
	public List<Utente> getAllUtentiInAula(Long aula_id); 
		
	@Query("SELECT DISTINC a FROM aula a JOIN FETCH a.utente WHERE a.id = :utente_id ")
	public Utente getUtenteByIdInAula(Long aulaId, Long utenteId);
	
}
