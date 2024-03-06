package it.akt.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.akt.models.TemaQuiz;
import it.akt.models.Utente;
import it.akt.services.TemaQuizService;
import jakarta.servlet.http.HttpSession;

@Controller
public class TemaController {

	private TemaQuizService temaQuizService;

	public TemaController(TemaQuizService temaQuizService) {
		this.temaQuizService = temaQuizService;
	}

	@GetMapping("/listatemi")
	public String listaTemaQuiz(Model model, Utente utente, HttpSession session) {
		int ruoloUtente = (int) session.getAttribute("ruoloUtente");
		if (ruoloUtente == 0) {
			return "redirect:/404";
		} else {
			List<TemaQuiz> temi = temaQuizService.getAllTemi();
			model.addAttribute("listatemi", temi);
			return "listatemi";
		}
	}

	@GetMapping("/newtema")
	public String newTema(Model model,Utente utente , HttpSession session) {
		int ruoloUtente = (int) session.getAttribute("ruoloUtente");
		if(ruoloUtente==0){
		return "redirect:/404";
		} else {
		model.addAttribute("tema", new TemaQuiz());
		return "newtema";
		}
	}

	@PostMapping("/temaform")
	public String newTema(@ModelAttribute TemaQuiz tema) {
		temaQuizService.createTema(tema);
		return "redirect:/listatemi";
	}

	@GetMapping("/eliminatema/{id}")
	public String eliminaTema(@PathVariable(name = "id") Long id, Model model,Utente utente, HttpSession session) {
		int ruoloUtente = (int) session.getAttribute("ruoloUtente");
		if(ruoloUtente==0){
		return "redirect:/404";
		} else {
		TemaQuiz tema = temaQuizService.getTemaById(id);
		if (tema.getDomande().isEmpty()) {
			temaQuizService.deleteTemaById(id);
			return "redirect:/listatemi";
		} else {
			model.addAttribute("s_alert", true);
		}
		return "listatemi";
	}
	}
}
