package it.akt.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.akt.models.Utente;
import it.akt.services.UtenteService;

@Controller
public class StudentiController {
	
	private UtenteService utenteService ;
	
	public StudentiController (UtenteService utenteService) {
		this.utenteService=utenteService;
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
	
}
