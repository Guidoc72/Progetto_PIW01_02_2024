package it.akt.repositories;

import java.util.List;
import org.springframework.data.repository.ListCrudRepository;

import it.akt.models.Domanda;

public interface DomandaRepository extends ListCrudRepository <Domanda, Long> {
	
	 public List<Domanda> findDomandaByTema(TemaQuiz tema);
	 
	 public Domanda findDomandaById (Long id);
	 
}
