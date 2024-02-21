package it.akt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.Domanda;

@Repository
public interface DomandaRepository extends ListCrudRepository <Domanda, Long> {
	
	@Query("SELECT d FROM Domande d JOIN d.tema t WHERE t.nome = :tema")
	 public List<Domanda> findDomandaByTema(TemaQuiz tema);
	 
	 public Domanda findDomandaById (Long id);
	 
}
