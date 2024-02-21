package it.akt.services;

import java.time.LocalDate;
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
import it.akt.repositories.QuizRepository;

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

	/**
	 * Costruttore della classe `QuizService`.
	 *
	 * @param quizRepository Il repository per i quiz.
	 */
    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    
    /**
     * Restituisce tutti i quiz creati.
     *
     * @param quiz L'oggetto quiz (non utilizzato nel metodo, ma richiesto dalla firma del metodo).
     * @return Una lista di oggetti Quiz presenti nel database.
     * @throws Exception Se si verifica un errore durante l'accesso al database o se non sono stati creati quiz.
     */
    public List<Quiz> findAllQuiz(Quiz quiz) throws Exception {
    	try {
            return quizRepository.findAll();
        } catch (Exception e) {
            System.out.println("Errore: Non è stato creato nessun Quiz!");
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
        	System.out.println("Errore: Quiz non trovato o non esistente con ID: " + id);
        	throw new Exception("Errore: Quiz non trovato o non esistente con ID: " + id);
        }    	
    }
    
    /**
     * Elimina un Quiz dato il suo ID.
     *
     * @param id L'ID del Quiz da eliminare.
     * @throws Exception Se il Quiz non viene trovato o non esiste con l'ID specificato,
     *                   oppure se l'eliminazione non riesce.
     */
    public void deleteQuizById(Long id) throws Exception{
    	
    	quizRepository.findById(id)
                .orElseThrow(() -> new Exception("Quiz non trovato o non esistente con ID: " + id));
    	
    	try {
    		quizRepository.deleteById(id);
    	} catch (Exception e) {
    		System.out.println("Errore: Eliminazione non riuscita del quiz: " +id+ " .");
    		throw new Exception("Errore: Eliminazione non riuscita del quiz: " +id+ " .");
    	}      	
    }
    
    /**
     * Crea un nuovo oggetto Quiz con i campi specificati.
     *
     * @param data La data del quiz.
     * @param temaQuiz Il tema del quiz.
     * @param aule L'insieme di aule associate al quiz.
     * @param risultati L'insieme di risultati del quiz.
     * @param domande L'insieme di domande del quiz.
     * @return L'oggetto Quiz creato.
     * @throws Exception Se i campi obbligatori sono vuoti.
     */
    public Quiz createQuiz(LocalDate data, TemaQuiz temaQuiz, Set<Aula> aule, Set<Risultato> risultati,
    		Set<Domanda> domande) throws Exception {
    	
        Quiz quiz = new Quiz();
        quiz.setData(data);
        quiz.setTemaQuiz(temaQuiz);
        quiz.setAule(aule);
        quiz.setRisultati(risultati);
        quiz.setDomande(domande);
        
        if(quiz.getData() == null || quiz.getTemaQuiz() == null || quiz.getAule() == null || quiz.getRisultati() == null || quiz.getDomande() == null) {
        	System.out.println("Errore: I campi non possono essere vuoti!");
        	throw new Exception("Errore: I campi non possono essere vuoti!");
        }
        return quizRepository.save(quiz);
    }
    
    /**
     * Aggiorna un oggetto Quiz esistente con i campi specificati.
     *
     * @param id L'ID del quiz da aggiornare.
     * @param data La nuova data del quiz.
     * @param temaQuiz Il nuovo tema del quiz.
     * @param aule Le nuove aule associate al quiz.
     * @param risultati I nuovi risultati del quiz.
     * @param domande Le nuove domande del quiz.
     * @return L'oggetto Quiz aggiornato.
     * @throws Exception Se uno o più campi obbligatori sono vuoti o se il quiz con l'ID specificato non esiste.
     */
    public Quiz updateQuizById(Long id, LocalDate data, TemaQuiz temaQuiz, Set<Aula> aule, Set<Risultato> risultati,
    		Set<Domanda> domande) throws Exception {
    	
    	//Controlla se il Quiz specificato con l'id esiste, in caso contrario manda un messaggio di errore.
    	Quiz quizEsistente = quizRepository.findById(id)
                .orElseThrow(() -> new Exception("Quiz non trovato o non esistente con ID: " + id));

    	quizEsistente.setData(data);
    	quizEsistente.setTemaQuiz(temaQuiz);
        quizEsistente.setAule(aule);
        quizEsistente.setRisultati(risultati);
        quizEsistente.setDomande(domande);
        
      //controlla se tutti i dati sono stati inseriti (Not Empty) e crea il Quiz, in caso contrario manda un messaggio di errore.
        if(quizEsistente.getData() == null || quizEsistente.getTemaQuiz() == null || quizEsistente.getAule() == null || quizEsistente.getRisultati() == null || quizEsistente.getDomande() == null) {
        	System.out.println("Errore: I campi non possono essere vuoti!");
        	throw new Exception("Errore: I campi non possono essere vuoti!");
        }
        return quizRepository.save(quizEsistente);    
    }
    
    /**
     * Restituisce un insieme di oggetti Quiz associati a una specifica domanda.
     *
     * @param domanda La domanda per cui cercare i quiz associati.
     * @return L'insieme di quiz associati alla domanda.
     * @throws Exception Se non ci sono quiz associati alla domanda.
     */
    public Set<Quiz> getQuizByDomande(Domanda domanda) throws Exception {
    	try {
            return quizRepository.findQuizbyDomande(domanda);
        } catch (Exception e) {
            System.out.println("Errore: Nessun Quiz è associato alla domanda!");
            throw new Exception("Errore: Nessun Quiz è associato alla domanda!");
        }
    }

    /**
     * Restituisce un insieme di oggetti Quiz associati a una specifica aula.
     *
     * @param aula L'aula per cui cercare i quiz associati.
     * @return L'insieme di quiz associati all'aula.
     * @throws Exception Se non ci sono quiz associati all'aula.
     */
    public Set<Quiz> getQuizByAule(Aula aula) throws Exception {
    	try {
    		return quizRepository.findQuizbyAule(aula);
        } catch (Exception e) {
            System.out.println("Errore: Nessun Quiz è associato all'Aula!");
            throw new Exception("Errore: Nessun Quiz è associato all'Aula!");
        }
    }
    
    /**
     * Restituisce un insieme di oggetti Quiz associati a un utente specifico.
     *
     * @param utente L'utente per cui cercare i quiz associati.
     * @return L'insieme di quiz associati all'utente.
     * @throws Exception Se non ci sono quiz associati all'utente.
     */
    public Set<Quiz> getQuizByUtente(Utente utente) throws Exception {
    	try {
    		return quizRepository.findQuizByUtenti(utente);
        } catch (Exception e) {
            System.out.println("Errore: Nessun Quiz è associato all'utente!");
            throw new Exception("Errore: Nessun Quiz è associato all'utente!");
        }
    }
    
    /**
     * Restituisce una lista di oggetti Quiz associati a un tema specifico.
     *
     * @param temaQuiz Il tema per cui cercare i quiz associati.
     * @return La lista di quiz associati al tema.
     * @throws Exception Se non ci sono quiz associati al tema.
     */
    public List<Quiz> getQuizByTema(TemaQuiz temaQuiz) throws Exception {
    	try {
    		return quizRepository.findQuizByTemaQuiz(temaQuiz);
        } catch (Exception e) {
            System.out.println("Errore: Nessun Quiz è associato al Tema!");
            throw new Exception("Errore: Nessun Quiz è associato al Tema!");
        }
    }
    
}
