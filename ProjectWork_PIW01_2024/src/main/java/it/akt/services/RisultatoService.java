package it.akt.services;


import it.akt.models.Risultato;
import it.akt.repositories.RisultatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RisultatoService {
    private RisultatoRepository risultatoRepository;



    public void addRisultato(Risultato risultato) {
        risultatoRepository.save(risultato);
    }

    public void deleteRisultatoById(Long id) {
        risultatoRepository.deleteById(id);
    }

    public List<Risultato> findAllByQuiz(Long id) {
        return risultatoRepository.findByQuizId(id).orElseThrow(() ->
                new RuntimeException(String.format("Non ci sta nessuna risposta per il Quiz con id %d", id) ));

    }
    public List<Risultato> findAllByUtenteId(Long id) {
        return risultatoRepository.findAllByUtenteId(id).orElseThrow(() ->
                new RuntimeException(String.format("Questo utente con id %d non ha nessuna risposta", id) ));

    }

    public Risultato findByUtenteIdAndQuizId(Long utente_id, Long quiz_id) {
        return risultatoRepository.findByUtenteIdAndQuizId(utente_id, quiz_id).orElseThrow(() ->
                new RuntimeException(String.format("Questo utente con id %d non ha una risposta al quiz con id : %d", utente_id, quiz_id) ));
    }

}

