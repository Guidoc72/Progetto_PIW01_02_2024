package it.akt.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.akt.models.Utente;
import it.akt.services.UtenteService;
import jakarta.validation.Valid;

@Controller
@SessionAttributes ("saveResultMessage")
public class RegistrationController {

	DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMM");
	String data = LocalDateTime.now().format(formato);
	
	@Autowired
	private UtenteService utenteService;
	
	public RegistrationController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}
	
	@GetMapping(path = "/registration")
	public String getRegistration(@RequestParam(required = false) boolean saving, Model model, Utente utente) {
		
		if(!saving)
			model.addAttribute("saveResultMessage", "");
		if (utente != null) {
			model.addAttribute("new_utente", utente);
		}else 
			model.addAttribute("new_utente", new Utente());
		return "registration";
	}
	
	
	/**
	 * Riceve i dati del form di registrazione, li elabora e salva l'utente. I caso di incongruenza dei dati con i dati di validazione
	 * rimanda al Form
	 * le annotazioni @Valid e @BindingResult servono per gestire la validazione. BindingResult deve seguire l'elemento Valid e serve per catturare gli errori
	 * @param user
	 * @param errors
	 * @param model
	 * @return String: rimanda alla pagina di registrazione
	 */
	
	@PostMapping("/richiestaRegistro")
	public String getRegistrationRequest(@ModelAttribute @Valid Utente utente, Errors errors, Model model) {
		
		//Creo il codice che dovrebbe inserire l'utente in caso sia un docente
		String codiceDaInserire = (utente.getNome().length() > 2 && utente.getCognome().length() > 2) ? utente.getNome().substring(0,2) + utente.getCognome().substring(0, 2) + data : "";
		boolean codiceCorretto = false;
		if(utente.getCodiceDocente() != "") {
						if(!codiceDaInserire.toLowerCase().equals(utente.getCodiceDocente().toLowerCase())) {
							codiceCorretto = false;
							model.addAttribute("message", "Il codice inserito non è corretto. Riprovaci!");
							//model.addAttribute("errors", errors.getAllErrors());
						}else {
							codiceCorretto = true;
							utente.setRuolo(1);
						}
						
						
			}
			if (errors.hasErrors() ) {
				model.addAttribute("errors", errors.getAllErrors());
				model.addAttribute("new_utente", utente);
				model.addAttribute("saveResultMessage", "Compila correttamente tutti i campi richiesti (*)!");
			
				return "registration";
				
			}else {
				if(utenteService.createNewUtente(utente) != null ) {
					if(codiceCorretto) {
						utente.setRuolo(1);
					}
				
					
				
					model.addAttribute("saveResultMessage", "Il suo profilo è stato salvato correttamente");
				}else {
					model.addAttribute("saveResultMessage", "Si è verificato un errore nel salvataggio del tuo profilo! Riprova... ");
				}
				return "redirect:registration?saving=true";
			}
			
		}
}
