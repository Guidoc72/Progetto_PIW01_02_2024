package it.akt.controllers;

 import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.akt.models.Domanda;
import it.akt.models.TemaQuiz;
import it.akt.models.Utente;
import it.akt.services.DomandaService;
import it.akt.services.TemaQuizService;
import jakarta.servlet.http.HttpSession;

@Controller
public class DomandaController {
	
	private DomandaService domandaService;
	private TemaQuizService temaQuizService;
	
	public DomandaController (DomandaService domandaService, TemaQuizService temaQuizService) {
		this.domandaService=domandaService;
		this.temaQuizService=temaQuizService;
	}
	
	@GetMapping("/newdomanda")
	public String newdomanda (Model model,Utente utente,HttpSession session){
		int ruoloUtente = (int) session.getAttribute("ruoloUtente");
		if(ruoloUtente==0){
		return "redirect:/404";
		} else {
		List<TemaQuiz> temaList = temaQuizService.getAllTemi();		
		model.addAttribute("domanda", new Domanda());
		model.addAttribute("temi", temaList);
		return "newdomanda";
	}
	}
	@PostMapping("/domandaform")
	public String newdomanda (@RequestParam ("temaquiz")String temaquiz, @ModelAttribute Domanda domanda) {
		domanda.setTema(temaQuizService.getTemaById(Long.parseLong(temaquiz)));
		domandaService.addDomanda(domanda);
		return "redirect:/listatemi";
	}
	
	@GetMapping("/listadomande/{id}")
	public String listaDomande (@PathVariable(name="id") Long id, Model model,Utente utente,HttpSession session) {
		int ruoloUtente = (int) session.getAttribute("ruoloUtente");
		if(ruoloUtente==0){
		return "redirect:/404";
		} else {
		model.addAttribute("listadomande",domandaService.findDomandaByTemaId(id));
		return "listadomande";
	}
	}
	@GetMapping("/eliminadomanda/{id}")
	public String eliminaDomanda (@PathVariable(name="id") Long id, Model model,Utente utente,HttpSession session) {
		int ruoloUtente = (int) session.getAttribute("ruoloUtente");
		if(ruoloUtente==0){
		return "redirect:/404";
		} else {
		Domanda domanda = domandaService.findDomandaById(id);
		if(domanda.getQuiz().isEmpty()) {
			domandaService.deleteDomandaById(id);
			return "redirect:/listadomande/"+id;
		} else {
			model.addAttribute("s_alert", true);		
		}			
		return "listadomande";
	}	
	}	
}
