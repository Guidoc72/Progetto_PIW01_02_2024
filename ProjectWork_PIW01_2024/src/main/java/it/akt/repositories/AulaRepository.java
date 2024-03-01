package it.akt.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.akt.models.Aula;

@Repository
public interface AulaRepository extends ListCrudRepository<Aula, Long> {
	
	public Aula findByUtentiId(Long id);
}