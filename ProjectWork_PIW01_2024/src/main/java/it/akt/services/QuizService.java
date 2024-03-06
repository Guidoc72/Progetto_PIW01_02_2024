package it.akt.services;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.akt.models.Quiz;
import it.akt.models.Risultato;
import it.akt.models.TemaQuiz;
import it.akt.models.Utente;
import it.akt.models.Domanda;
import it.akt.models.Aula;
import it.akt.repositories.AulaRepository;
import it.akt.repositories.DomandaRepository;
import it.akt.repositories.QuizRepository;
import it.akt.repositories.RisultatoRepository;
import it.akt.repositories.TemaQuizRepository;
import it.akt.repositories.UtenteRepository;


/**
 * Questa classe rappresenta un servizio per la gestione dei quiz.
 * Contiene metodi per interagire con gli oggetti di tipo Quiz.
 *
 * @author Samuele Romanelli
 */
@Service
public class QuizService {
	
	/**
	 * Repository per la gestione dei quiz.
	 */
	private final QuizRepository quizRepository;
	private final DomandaRepository domandaRepository;
	private final TemaQuizRepository temaQuizRepository;
	private final AulaRepository aulaRepository;
	private final UtenteRepository utenteRepository;
	private final RisultatoRepository risultatoRepository;

	/**
	 * Costruttore della classe `QuizService`.
	 *
	 * @param quizRepository Il repository per i quiz.
	 */
    @Autowired
    public QuizService(QuizRepository quizRepository, DomandaRepository domandaRepository, TemaQuizRepository temaQuizRepository, AulaRepository aulaRepository, UtenteRepository utenteRepository, RisultatoRepository risultatoRepository) {
        this.quizRepository = quizRepository;
        this.domandaRepository = domandaRepository;
        this.temaQuizRepository = temaQuizRepository;
        this.aulaRepository = aulaRepository;
        this.utenteRepository = utenteRepository;
        this.risultatoRepository = risultatoRepository;
    }
    
    /**
     * Restituisce tutti i quiz creati.
     *
     * @param quiz L'oggetto quiz (non utilizzato nel metodo, ma richiesto dalla firma del metodo).
     * @return Una lista di oggetti Quiz presenti nel database.
     * @throws Exception Se si verifica un errore durante l'accesso al database o se non sono stati creati quiz.
     */
    public List<Quiz> findAllQuiz() throws Exception {
    	try {
            return quizRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Errore: Non è stato creato nessun Quiz!");
        }    	
    }
    
    /**
     * Trova un oggetto Quiz dato il suo ID.
     *
     * @param id L'ID del Quiz da cercare.
     * @return L'oggetto Quiz corrispondente all'ID specificato.
     * @throws Exception Se il Quiz non viene trovato o non esiste con l'ID specificato.
     */
    public Quiz findQuizById(Long id) throws Exception{
    	Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            return quiz.get();
        } else {
        	throw new Exception("Errore: Quiz non trovato o non esistente con ID: " + id);
        }    	
    }
    
    /**
     * Elimina un quiz dato il suo ID.
     *
     * @param id L'ID del quiz da eliminare.
     * @return Il quiz eliminato o `null` se non è stato trovato.
     * @throws Exception Se il quiz con l'ID specificato non esiste.
     */
    public String deleteQuizById(Long id) {
        try {
            Quiz quiz = quizRepository.findById(id).orElseThrow();

            for (Utente utente : quiz.getUtente()) {
                utente.getQuiz().remove(quiz);
                utenteRepository.save(utente);
            }

            for (Aula aula : quiz.getAule()) {
                aula.getQuiz().remove(quiz);
                aulaRepository.save(aula);
            }

            for (Risultato risultato : quiz.getRisultati()) {
                risultatoRepository.delete(risultato);
            }

            quizRepository.delete(quiz);
            return null;
        } catch (Exception e) {
            return "Errore: Quiz non trovato o non esistente con ID: " + id;
        }
    }
    
    /**
     * Crea un nuovo quiz e lo salva nel repository.
     *
     * @param quiz Il quiz da creare e salvare.
     * @return Il quiz creato e salvato.
     * @throws Exception Se i campi Data o Tema sono vuoti.
     */
    public Quiz createQuiz(Quiz quiz) throws Exception {
    	
        if(quiz.getData() == null || quiz.getTemaQuiz() == null) {
        	throw new Exception("Errore: I campi Data e Tema non possono essere vuoti!");
        }
        
        Set<Domanda> tutteLeDomandeSet = domandaRepository.findAllByTemaId(quiz.getTemaQuiz().getId());
        List<Domanda> tutteLeDomandeList = new ArrayList<>(tutteLeDomandeSet);
        if (tutteLeDomandeList.size() < 10) {
            throw new Exception("Non ci sono abbastanza domande per creare il quiz.");
            
        }
        
        
        return quizRepository.save(quiz);
    }
    
    /**
     * Genera un quiz di 10 domande casuali basate sul tema specificato.
     *
     * @param tema Il tema del quiz.
     * @param id   L'ID del quiz da generare.
     * @return L'oggetto Quiz generato con 10 domande casuali.
     * @throws Exception Se non ci sono abbastanza domande per generare il quiz 
     */
    public Quiz generaQuizCasuale(Quiz quiz) throws Exception {
        // Recupera tutte le domande dal repository
        Set<Domanda> tutteLeDomandeSet = domandaRepository.findAllByTemaId(quiz.getTemaQuiz().getId());
        List<Domanda> tutteLeDomandeList = new ArrayList<>(tutteLeDomandeSet);
        Collections.shuffle(tutteLeDomandeList);

        // Controllo se ci sono abbastanza domande
        if (tutteLeDomandeList.size() < 10) {
            throw new Exception("Non ci sono abbastanza domande per generare il quiz.");
            
        }
        
        tutteLeDomandeList = tutteLeDomandeList.subList(0, 10); // Prende le prime 10 domande
            
            for (int i = 0; i < 10; i++) {
            quiz.getDomande().add(tutteLeDomandeList.get(i));
            }
            
			return quizRepository.save(quiz);
        }
        
    /**
     * Assegna le aule al quiz specificato.
     *
     * @param id   L'ID del quiz da assegnare alle aule.
     * @param aule La lista delle aule da assegnare al quiz.
     * @return L'oggetto Quiz aggiornato con le aule assegnate.
     * @throws Exception Se non sono state selezionate aule o se il quiz non è trovato o non esistente.
     */
	   public Quiz assegnaAule(Long id, List<Aula> aule) throws Exception {
		   
		   
		   
		   if(aule.size()==0) {
			   throw new Exception("Non sono state selezionate aule per assegnare il quiz!");
		   }
		   
		   Quiz quiz = quizRepository.findById(id)
				   .orElseThrow(() -> new Exception("Quiz non trovato o non esistente con ID: " + id));
		   
		   for(int i = 0; i < aule.size(); i++) {
			   
			   List<Utente> utente = utenteRepository.findByAuleId(aule.get(i).getId());
			   quiz.getAule().add(aule.get(i));			   
			   for(int i2 = 0; i2 < utente.size(); i2++) {
				   quiz.getUtente().add(utente.get(i));
			   }
		   }
			return quizRepository.save(quiz);
	   }
    
    /**
     * Recupera un insieme di domande associate all'ID del quiz specificato.
     *
     * @param id L'ID del quiz.
     * @return Un insieme di domande correlate al quiz.
     * @throws Exception Se non ci sono domande nel quiz.
     */
    public Set<Domanda> getDomandeByQuizId(Long id) throws Exception {
    	
    	Set<Domanda> domandeByQuiz = domandaRepository.findAllByQuizId(id);
    	
    	return domandeByQuiz;
    }

    /**
     * Restituisce un insieme di temi unici.
     *
     * @return Un insieme di oggetti TemaQuiz senza duplicati.
     * @throws Exception Se non è stato trovato alcun tema.
     */
    public Set<TemaQuiz> getAllTemi() throws Exception {
        try {
            List<TemaQuiz> allTemi = temaQuizRepository.findAll();
            return new HashSet<>(allTemi); // Converte la lista in un Set
        } catch (Exception e) {
            throw new Exception("Errore: Non è stato trovato nessun Tema!");
        }
    }
    
    /**
     * Restituisce un insieme di tutte le aule.
     *
     * @return un insieme di oggetti Aula.
     * @throws Exception se si verifica un errore durante l'elaborazione.
     */
    public Set<Aula> getAllAule() throws Exception {
    	
    	try {
    		List<Aula> allAule = aulaRepository.findAll();
    		return new HashSet<>(allAule);    		
    	}catch (Exception e) {
            throw new Exception("Errore: Non è stato trovata nessun Aula!");    		
    	}    	
    }
    
    /**
     * Restituisce tutti i quiz associati ad un utente specifico.
     * @param 	id utente Utente
     * @return 	Set Quiz
     */
    public Set<Quiz> findQuizByUtenteId(Long id) {
    	return quizRepository.findByUtentiId(id);
    }
}
