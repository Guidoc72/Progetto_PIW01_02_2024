package it.akt.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.akt.models.Domanda;
import it.akt.models.TemaQuiz;
import it.akt.services.DomandaService;
import it.akt.services.TemaQuizService;

@Controller
public class DomandaController {
	
	private DomandaService domandaService;
	private TemaQuizService temaQuizService;
	
	public DomandaController (DomandaService domandaService, TemaQuizService temaQuizService) {
		this.domandaService=domandaService;
		this.temaQuizService=temaQuizService;
	}
	
	@GetMapping("/newdomanda")
	public String newdomanda (Model model){
		List<TemaQuiz> temaList = temaQuizService.getAllTemi();		
		
		model.addAttribute("domanda", new Domanda());
		model.addAttribute("temi", temaList);
		return "newdomanda";
	}
	
	@PostMapping("/domandaform")
	public String newdomanda (@RequestParam ("temaquiz")String temaquiz, @ModelAttribute Domanda domanda) {
		
		Long temaId = Long.parseLong(temaquiz);
		
		TemaQuiz tema = temaQuizService.getTemaById(temaId);
		
		domanda.setTema(tema);

		domandaService.addDomanda(domanda);

		return "newdomanda";
	}
	

}
