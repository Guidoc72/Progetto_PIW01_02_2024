package it.akt.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.Quiz;

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
	
}