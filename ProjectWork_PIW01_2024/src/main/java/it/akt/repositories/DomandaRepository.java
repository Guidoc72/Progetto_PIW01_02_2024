package it.akt.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.Domanda;
import it.akt.models.TemaQuiz;
@Repository
public interface DomandaRepository extends ListCrudRepository <Domanda, Long> {
	
	@Query("SELECT d FROM Domanda d JOIN d.tema t WHERE t.nome = : tema")
	 public Set<Domanda> findDomandaByTema(TemaQuiz tema);
	
//	@Query("SELECT d FROM Domanda d JOIN tema t on d.tema_id = t.id WHERE t.id = : id")
	 public List<Domanda> findAllByTemaId(Long id);
	 
	 public Domanda findDomandaById (Long id);	 
	 
//	 @Query("SELECT d FROM Domanda d JOIN quiz q on d.id = q.id WHERE d.id = :id")
	 public Set<Domanda> findAllByQuizId(Long id);
	 
}
