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
	
	//Restituisce i Quiz associati alle Domande
	Set<Quiz> findQuizbyDomande(Domanda domande);
	
	//Restituisce i Quiz associati ad un Aula
	Set<Quiz> findQuizbyAule(Aula aule);
	
	//Restituisce i Quiz associati ad un Utente
	Set<Quiz> findQuizByUtenti(Utente utenti);
	
	//Restituisce i Quiz associati a un Tema
	List<Quiz> findQuizByTemaQuiz(TemaQuiz temaQuiz);



}