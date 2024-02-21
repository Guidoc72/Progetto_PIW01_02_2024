package it.akt.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import it.akt.models.Quiz;
import it.akt.models.TemaQuiz;

//Repository per la tabella "TemaQuiz"
public interface TemaQuizRepository extends ListCrudRepository<TemaQuiz, Long> {

	//PER QUIZ:
	//Recupero dei quiz per un tema specifico
	List<Quiz> findByTemaQuiz(TemaQuiz tema);
	
	//Conteggio dei quiz per un tema
	long countByTemaQuiz(TemaQuiz tema);
	
	//Recupero dei temi per un quiz specifico
	List<TemaQuiz> findByQuiz(Quiz quiz);
	
	
	
	
	//PER DOMANDA:
	//Recupero delle domande per un tema specifico
	 List<Domanda> findByTema(TemaQuiz tema);
	 
	 //Conteggio delle domande per un tema
	 long countByTema(TemaQuiz tema);
	 
	 //Recupero dei temi per una domanda specifica
	 List<TemaQuiz> findByDomanda(Domanda domanda);
	
}
