package it.akt.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.akt.models.Domanda;
import it.akt.models.Quiz;
import it.akt.models.Risultato;
import it.akt.models.Utente;
import it.akt.services.QuizService;
import it.akt.services.UtenteService;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentiController {

	private UtenteService utenteService;
	private QuizService quizService;

	public StudentiController(UtenteService utenteService, QuizService quizService) {
		this.utenteService = utenteService;
		this.quizService = quizService;
	}

	@GetMapping("/listastudenti")
	public String listaStudenti(Model model, Utente utente, HttpSession session) {
		int ruoloUtente = (int) session.getAttribute("ruoloUtente");
		if (ruoloUtente == 0) {
			return "redirect:/404";
		} else {
			List<Utente> listaStudenti = utenteService.findAllByRuolo(1); // 1 è il ruolo dello studente
			model.addAttribute("listastudenti", listaStudenti);
			return "listastudenti";
		}
	}

	@GetMapping("/modificastudente/{id}")
	public String modificaStudente(@PathVariable(name = "id") Long id, Model model, Utente utente,
			HttpSession session) {
		int ruoloUtente = (int) session.getAttribute("ruoloUtente");
		if (ruoloUtente == 0) {
			return "redirect:/404";
		} else {
			model.addAttribute("studente", utenteService.getUtenteById(id));
			return "modificastudente";
		}
	}

	@PostMapping("/utenteform")
	public String studenteForm(@ModelAttribute Utente utente) {
		utenteService.updateUtente(utente);
		return "redirect:/listastudenti";
	}

	/**
	 * Gestisce la chiamata HTTP GET all'url "/esegui-quiz". Restituisce la view con
	 * il quiz associato al tema che l'utente ha scelto nella pagina home.
	 * 
	 * @param id    utente Utente
	 * @param id    quiz Quiz
	 * @param model Model
	 * @return /esegui-quiz
	 * @throws Exception
	 */
	@GetMapping("/esegui-quiz/{idUtente}/{idQuiz}")
	public String eseguiQuiz(@PathVariable Long idUtente, @PathVariable Long idQuiz, Model model, Utente utente,
			HttpSession session) throws Exception {
		int ruoloUtente = (int) session.getAttribute("ruoloUtente");
		if (ruoloUtente == 1) {
			return "redirect:/404";
		} else {
			Quiz quiz = quizService.findQuizById(idQuiz);
			System.out.println("TemaQuiz: " + quiz.getTemaQuiz().getNome());
			Set<Domanda> domande = quizService.getDomandeByQuizId(idQuiz);
			model.addAttribute("domande", domande);
			model.addAttribute("quiz", quiz);
			model.addAttribute("idUtente", idUtente.toString());
			model.addAttribute("risultato", new Risultato());
			return "esegui-quiz";
		}
	}
}