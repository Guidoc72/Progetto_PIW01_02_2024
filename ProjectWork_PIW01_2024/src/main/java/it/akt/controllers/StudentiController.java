package it.akt.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.akt.models.Quiz;
import it.akt.models.Utente;
import it.akt.services.QuizService;
import it.akt.services.UtenteService;

@Controller
public class StudentiController {
	
	private UtenteService utenteService ;
	private QuizService quizService;
	
	public StudentiController (UtenteService utenteService, QuizService quizService) {
		this.utenteService = utenteService;
		this.quizService = quizService;
	}

	@GetMapping("/listastudenti")
	public String listaStudenti (Model model) {
		List<Utente> listaStudenti = utenteService.findAllByRuolo(1); //1 è il ruolo dello studente
		model.addAttribute("listastudenti", listaStudenti);	
		return "listastudenti";
	}
	
	@GetMapping("/modificastudente/{id}")
	public String modificaStudente(@PathVariable(name="id") Long id, Model model) {
		model.addAttribute("studente", utenteService.getUtenteById(id));
		return "modificastudente";
	}
	
	@PostMapping("/utenteform")
	public String studenteForm (@ModelAttribute Utente utente ) {
		utenteService.updateUtente(utente);
		return "redirect:/listastudenti";
	}
	
	/**
	 * Gestisce la chiamata HTTP GET all'url "/home".
	 * Restituisce la view dell'utente che ha effettuato il log.
	 * Condizione con @PreAuthorized: 
	 * Se l'utente ha ruolo 1(=studente) vede i quiz che a disposizione in base all'aula a cui è assegnato.
	 * Se l'utente ha ruolo 0(=docente) non vede i quiz.
	 * @param 	id utente Utente
	 * @param 	model Model
	 * @return	/home
	 */
	@GetMapping("/home/{id}") //"/home/{ruolo}/{id}?
	//??@PreAuthorize("hasRole('0') or hasRole('USER')")??
	/*
	 * Questa annotazione verifica se l'utente corrente ha uno dei ruoli specificati tra parentesi. 
	 * In questo caso, l'utente deve avere il ruolo "1" oppure il ruolo "ADMIN" per accedere a questa pagina.
	 * Se l'utente non ha uno dei ruoli richiesti, verrà generata un'eccezione AccessDeniedException.
	 * Annotazione per rendere visibile i quiz solo allo studente (0)
	 */
	public String getQuizByUtenteId(@PathVariable Long id, Model model) {
	    Set<Quiz> quizzes = quizService.findQuizByUtenteId(id); 
	    Utente utente = utenteService.getUtenteById(id);
	    model.addAttribute("quizzes", quizzes);
	    model.addAttribute("utente", utente); //per concatenazione!
	    return "home";
	}
	
	/**
	 * Gestisce la chiamata HTTP GET all'url "/esegui-quiz".
	 * Restituisce la view con il quiz associato al tema che l'utente ha scelto nella pagina home.
	 * @param 	id utente Utente
	 * @param 	id quiz Quiz
	 * @param 	model Model
	 * @return 	/esegui-quiz
	 * @throws 	Exception
	 */
	@GetMapping("/esegui-quiz/{idUtente}/{idQuiz}") 
	public String eseguiQuiz(@PathVariable Long idUtente, @PathVariable Long idQuiz, 
							Model model) throws Exception  {
		Quiz quiz = quizService.findQuizById(idQuiz);
		model.addAttribute("quiz", quiz);
	    model.addAttribute("idUtente", idUtente);
        return "esegui-quiz";
	}
}
