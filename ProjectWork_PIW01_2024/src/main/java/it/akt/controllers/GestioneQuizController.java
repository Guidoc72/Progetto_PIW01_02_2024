package it.akt.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.akt.models.Aula;
import it.akt.models.Domanda;
import it.akt.models.Quiz;
import it.akt.models.TemaQuiz;
import it.akt.services.QuizService;

/**
 * @author Samuele Romanelli
 */
@Controller
public class GestioneQuizController {
	
	@Autowired
	private QuizService quizService;
	
	/**
	 * Restituisce la pagina "gestionequiz" con i dati del quiz.
	 *
	 * @param model il modello per aggiungere attributi alla vista
	 * @return il nome della vista Thymeleaf da visualizzare
	 * @throws Exception se si verifica un errore durante l'esecuzione
	 */
	@GetMapping("/gestionequiz")
    public String gestioneQuizPage(Model model) throws Exception {
		List<Quiz> listaQuiz = quizService.findAllQuiz();
        model.addAttribute("listaQuiz", listaQuiz);
        //Toast delete check
        if (model.containsAttribute("showToast")) {
            model.addAttribute("showToast", true);
        }
        //Toast create check
        if (model.containsAttribute("showToast2")) {
        	model.addAttribute("showToast2", true);
        }
        return "gestionequiz"; 
    }
	
	/**
	 * Restituisce la pagina "checkquiz" con i dati del quiz specificato dall'ID.
	 *
	 * @param idQuiz l'ID del quiz da visualizzare
	 * @param model il modello per aggiungere attributi alla vista
	 * @return il nome della vista Thymeleaf da visualizzare ("checkquiz")
	 * @throws Exception se si verifica un errore durante l'esecuzione
	 */
	@GetMapping("gestionequiz/check/{idQuiz}")
	public String checkQuiz(@PathVariable(name = "idQuiz") Long idQuiz, Model model) throws Exception {
		Quiz checkQuiz = quizService.findQuizById(idQuiz);
		Set<Domanda> domande = quizService.getDomandeByQuizId(idQuiz);
		model.addAttribute("domande", domande);
		model.addAttribute("checkQuiz", checkQuiz);
	    return "checkquiz";
	}
	
	/**
	 * Elimina un quiz specificato dall'ID e restituisce la pagina "conferma-eliminazione".
	 *
	 * @param idQuiz l'ID del quiz da eliminare
	 * @param model il modello per aggiungere attributi alla vista
	 * @return il nome della vista Thymeleaf da visualizzare ("conferma-eliminazione")
	 * @throws Exception se si verifica un errore durante l'esecuzione
	 */
	@GetMapping("gestionequiz/delete/{idQuiz}")
	public String deleteQuiz(@PathVariable(name = "idQuiz") Long idQuiz, Model model) throws Exception { 
		try {
		Quiz deleteQuiz = quizService.deleteQuizById(idQuiz);
		model.addAttribute("messaggio", "true");
		}catch (Exception e) {
			model.addAttribute("messaggio", "false");
		}
		return "conferma-eliminazione";
	}
	
	/**
	 * Questo metodo gestisce l'evento di clic sul pulsante per eliminare un quiz.
	 * Viene utilizzato l'annotazione @PostMapping per mappare la richiesta HTTP POST
	 * all'endpoint "/toastdelete".
	 *
	 * @param redirectAttributes Attributi di reindirizzamento per mostrare il toast.
	 * @return Una stringa che reindirizza alla pagina "/gestionequiz".
	 */
	@PostMapping("/toastdelete")
	public String handleButton(RedirectAttributes redirectAttributes) {
	    redirectAttributes.addFlashAttribute("showToast", true);
	    // Stampa un messaggio di debug per verificare l'invio del toast.
	    System.out.println("Toast inviato a Gestionequiz.html");
	    return "redirect:/gestionequiz";
	}

	/**
	 * Questo metodo gestisce l'evento di clic sul pulsante per creare un nuovo quiz.
	 * Viene utilizzato l'annotazione @PostMapping per mappare la richiesta HTTP POST
	 * all'endpoint "/toastcreate".
	 *
	 * @param redirectAttributes Attributi di reindirizzamento per mostrare il secondo toast.
	 * @return Una stringa che reindirizza alla pagina "/gestionequiz".
	 */
	@PostMapping("/toastcreate")
	public String handleButton2(RedirectAttributes redirectAttributes) {
	    redirectAttributes.addFlashAttribute("showToast2", true);
	    // Stampa un messaggio di debug per verificare l'invio del secondo toast.
	    System.out.println("Toast inviato a Gestionequiz.html");
	    return "redirect:/gestionequiz";
	}

	
	/**
	 * Restituisce la pagina "addquiz" con i dati necessari per creare un nuovo quiz.
	 *
	 * @param model il modello per aggiungere attributi alla vista
	 * @return il nome della vista Thymeleaf da visualizzare ("addquiz")
	 * @throws Exception se si verifica un errore durante l'esecuzione
	 */
	@GetMapping("gestionequiz/add")
	public String addQuiz(Model model) throws Exception {
		Set<TemaQuiz> temi = quizService.getAllTemi();
		model.addAttribute("temi", temi);
		return "addquiz";
	}
	
	/**
	 * Crea un nuovo quiz con il tema specificato e reindirizza alla pagina "assegnaaule".
	 *
	 * @param tema il tema del quiz da creare
	 * @param model il modello per aggiungere attributi alla vista
	 * @return il nome della vista Thymeleaf da visualizzare ("assegnaaule")
	 */
	 @PostMapping("gestionequiz/add")
	    public String creaQuiz(@RequestParam("tema") TemaQuiz tema, Model model) {
	        try {
	        Quiz nuovoQuiz = new Quiz();
	        LocalDate dataCreazione = LocalDate.now(); // Data corrente
	        nuovoQuiz.setData(dataCreazione);
	        nuovoQuiz.setTemaQuiz(tema);
	        
	        quizService.createQuiz(nuovoQuiz);
	        
	        System.out.print("Ho creato il quiz con id: " + nuovoQuiz.getId() + " con il Tema: " + nuovoQuiz.getTemaQuiz().getNome());
	        System.out.println();

	        
	        quizService.generaQuizCasuale(nuovoQuiz);
	        //prendo l'id del quiz appena creato
	        return "redirect:/gestionequiz/add/assegnaaule?idQuiz="+nuovoQuiz.getId();
	        
	        }catch (Exception e) {
		    System.out.println("Non ci sono abbastanza domande per generare il quiz!");
	        model.addAttribute("messaggio", "false");
	        return "successo";
	        }
	    }
	 
	 /**
	  * Restituisce la pagina "assegnaaule" con l'elenco delle aule disponibili per un quiz specificato dall'ID.
	  *
	  * @param idQuiz l'ID del quiz per il quale visualizzare le aule disponibili
	  * @param model il modello per aggiungere attributi alla vista
	  * @return il nome della vista Thymeleaf da visualizzare ("assegnaaule")
	  * @throws Exception se si verifica un errore durante l'esecuzione
	  */
	 @GetMapping("gestionequiz/add/assegnaaule")
	    public String mostraAule(@RequestParam(name = "idQuiz") Long id, Model model) throws Exception  {
	        Set<Aula> aule = quizService.getAllAule();
	        model.addAttribute("aule", aule);
	        model.addAttribute("idQuiz", id.toString());
	        return "assegnaaule";
	    }
	 
	 /**
	  * Assegna le aule selezionate a un quiz specificato dall'ID e restituisce la pagina "successoaula".
	  *
	  * @param auleSelezionate la lista delle aule da assegnare al quiz
	  * @param id l'ID del quiz a cui assegnare le aule
	  * @param model il modello per aggiungere attributi alla vista
	  * @return il nome della vista Thymeleaf da visualizzare ("successoaula")
	  * @throws Exception se si verifica un errore durante l'esecuzione
	  */
	 @PostMapping("gestionequiz/add/assegnaaule")
	 public String assegnaAule(@RequestParam("aule") List<Aula> auleSelezionate, @ModelAttribute("idQuiz") String id, Model model) throws Exception {
		
		 //stampa controllo creazione e assegnazione quiz con aula
		 System.out.print("Ho creato il quiz con id: " + id + " ed è stato assegnato alle aule: ");
		 for (Aula aula : auleSelezionate) {
		     System.out.print(aula.getNome() + " ");
		 }
		 System.out.println();

		
		if(auleSelezionate.size()==0) {
	        System.err.println("Non sono state selezionate aule per assegnare il quiz!");
	        model.addAttribute("messaggio", "false");
	    } else {
	        try {
	            quizService.assegnaAule(Long.parseLong(id), auleSelezionate);
	            model.addAttribute("messaggio", "true");
	        } catch (Exception e) {
	            System.err.println("Errore durante l'assegnazione delle aule al quiz!");
	            model.addAttribute("messaggio", "false");
	        }
	    }
	    return "successoaula";
		 
	 }
	 
}
