package it.akt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.akt.models.TemaQuiz;
import it.akt.repositories.TemaQuizRepository;

@Service
public class TemaQuizService {

    private final TemaQuizRepository temaQuizRepository;


    public TemaQuizService(TemaQuizRepository temaQuizRepository) {
        this.temaQuizRepository = temaQuizRepository;
    }

    // Recupero dei dati
    public List<TemaQuiz> getAllTemiQuiz() {
        return temaQuizRepository.findAll();
    }

    // Creazione e gestione dei temi dei quiz
    public TemaQuiz createTemaQuiz(TemaQuiz temaQuiz) {
        return temaQuizRepository.save(temaQuiz);
    }

    public TemaQuiz updateTemaQuiz(TemaQuiz temaQuiz) {
        return temaQuizRepository.save(temaQuiz);
    }

    // 3. Associazione tra temi e quiz
//    @Transactional
//    public void associateQuizWithTema(Long temaId, List<Quiz> quizList) {
//        TemaQuiz temaQuiz = temaQuizRepository.findById(temaId)
//                .orElseThrow(() -> new IllegalArgumentException("Tema non trovato"));
//        temaQuiz.setQuizList(quizList);
//        temaQuizRepository.save(temaQuiz);
//    }

    // 6. Paginazione e ordinamento
//    public List<TemaQuiz> getPaginatedTemiQuiz(int page, int size) {
//        return temaQuizRepository.findAll(PageRequest.of(page, size)).getContent();
//    }
}