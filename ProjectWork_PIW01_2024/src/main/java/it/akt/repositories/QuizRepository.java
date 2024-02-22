package it.akt.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.Aula;
import it.akt.models.Quiz;
import it.akt.models.TemaQuiz;
import it.akt.models.Domanda;
import it.akt.models.Utente;

/**
 * Questa interfaccia rappresenta un repository per la classe Quiz.
 * Estende l'interfaccia ListCrudRepository per fornire operazioni CRUD specifiche per i quiz.
 *
 * @param <Quiz> Il tipo di entità gestito dal repository (nel nostro caso, Quiz).
 * @param <Long> Il tipo dell'ID dell'entità (nel nostro caso, Long).
 * @author Samuele Romanelli
 */
@Repository
public interface QuizRepository extends ListCrudRepository<Quiz, Long> {
	
	/**
	 * Restituisce un insieme di oggetti Quiz associati a una specifica domanda.
	 *
	 * @param domanda La domanda per cui cercare i quiz associati.
	 */
	@Query("SELECT q FROM quiz q WHERE :domanda MEMBER OF q.domande")
	Set<Quiz> findQuizbyDomande(Domanda domanda);
	
	/**
	 * Restituisce un insieme di oggetti Quiz associati a una specifica aula.
	 *
	 * @param aula L'aula per cui cercare i quiz associati.
	 */
	@Query("SELECT q FROM quiz q WHERE :aula MEMBER OF q.aule")
	Set<Quiz> findQuizbyAule(Aula aula);
	
	/**
	 * Restituisce un insieme di oggetti Quiz associati a un utente specifico.
	 *
	 * @param utente L'utente per cui cercare i quiz associati.
	 */
	@Query("SELECT q FROM quiz q WHERE :utente MEMBER OF q.utenti")
	Set<Quiz> findQuizByUtenti(Utente utente);
	
	/**
	 * Restituisce una lista di oggetti Quiz associati a un tema specifico.
	 *
	 * @param temaQuiz Il tema per cui cercare i quiz associati.
	 */
	@Query("SELECT q FROM quiz q WHERE :tema MEMBER OF q.temi")
	List<Quiz> findQuizByTemaQuiz(TemaQuiz temaQuiz);
}