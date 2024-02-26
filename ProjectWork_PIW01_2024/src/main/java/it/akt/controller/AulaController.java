package it.akt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.akt.models.Aula;

@Controller
public class AulaController {
@GetMapping("/")
public String getAula (Model model) {
	Aula aula = new Aula();
	model.addAttribute("nomeAula", aula);
	return "aula";
	
	
}
}
