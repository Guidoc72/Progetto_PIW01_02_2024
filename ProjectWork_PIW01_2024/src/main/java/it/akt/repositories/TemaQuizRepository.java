package it.akt.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.TemaQuiz;

@Repository
public interface TemaQuizRepository extends ListCrudRepository<TemaQuiz, Long> {
	
		
	 
//	//PER QUIZ:
//	//Recupero dei quiz per un tema specifico
//	List<Quiz> findByTemaQuiz(TemaQuiz tema);
//	
//	//Recupero dei temi per un quiz specifico
//	List<TemaQuiz> findByQuiz(Quiz quiz);
//	
//	
//	//PER DOMANDA:
//	//Recupero delle domande per un tema specifico
//	 List<Domanda> findByTema(TemaQuiz tema);
//	 
//	 //Recupero dei temi per una domanda specifica
//	 List<TemaQuiz> findByDomanda(Domanda domanda);
	
}
