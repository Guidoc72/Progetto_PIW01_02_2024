package it.akt.repositories;

import it.akt.models.Risultato;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface RisultatoRepository extends ListCrudRepository<Risultato, Long>{
        Optional<List<Risultato>> findAllByUtenteId(Long id);

        Optional<Risultato> findByUtenteIdAndQuizId(Long utenteid, Long quizid);

        Optional<List<Risultato>> findByQuizId(Long id);
    }