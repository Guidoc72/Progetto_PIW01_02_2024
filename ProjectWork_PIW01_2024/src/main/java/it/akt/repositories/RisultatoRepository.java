package it.akt.repositories;

import it.akt.models.Risultato;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface RisultatoRepository extends ListCrudRepository<Risultato, Long>{
        Optional<List<Risultato>> findAllByUtenteId(Long id);
        Optional<Risultato> findByUtenteIdAndQuizId(Long utenteid, Long quizid);
    @Query("SELECT r FROM Risultato r JOIN r.quiz q JOIN q.aule a WHERE q.id = :quizId AND a.id = :aulaId")
    Optional<List<Risultato>> findAllByQuizIdAndAulaId(Long quizId, Long aulaId);
    }