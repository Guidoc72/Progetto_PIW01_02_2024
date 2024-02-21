package it.akt.repositories;

import org.springframework.data.repository.ListCrudRepository;
import it.akt.models.Quiz;

//Creazione Repository Table Quiz
public interface QuizRepository extends ListCrudRepository<Quiz, Long> {
    
	
	
	
}