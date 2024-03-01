package it.akt.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.TemaQuiz;

/**
 * Interfaccia di repository per la gestione delle entità TemaQuiz.
 * @author Federico
 */
@Repository
public interface TemaQuizRepository extends ListCrudRepository<TemaQuiz, Long> {

}