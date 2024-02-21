package it.akt.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.ListCrudRepository;

import it.akt.models.Aula;
import it.akt.models.Quiz;
import it.akt.models.TemaQuiz;
import it.akt.models.Domanda;

//Creazione Repository Table Quiz
public interface QuizRepository extends ListCrudRepository<Quiz, Long> {
	
	//Restituisce le domande associate ad un Quiz
	Set<Domanda> findDomandeByQuiz(Quiz quiz);
	
	//Restituisce le Aule asscoiate ad un Quiz 
	Set<Aula> findAuleByQuiz(Quiz quiz);

	//Restituisce i Quiz associati ad un Tema
	List<Quiz> findByTemaQuiz(TemaQuiz temaQuiz);
	
	
}