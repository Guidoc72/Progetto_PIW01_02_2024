package it.akt.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.akt.models.Aula;
import it.akt.models.Quiz;
import it.akt.models.Utente;
import it.akt.services.AulaService;
import it.akt.services.UtenteService;

@Controller
public class AulaController {

    private AulaService aulaService;
    private UtenteService utenteService;
    
    public AulaController(AulaService aulaService, UtenteService utenteService) {
        this.aulaService = aulaService;
        this.utenteService = utenteService;
    }
	
/**
 * Gestisce la chiamata HTTP GET all'URL "/gestione-aula".
 * Restituisce la view con l'elenco delle aule esistenti.
 * @param   model Model 
 * @return  /gestione-aula
 */

    @GetMapping("/gestione-aula")
    public String getAule(Model model) {
        List<Aula> aule = aulaService.getAllAule();
        model.addAttribute("aule", aule);
        return "gestione-aula"; 
    }

/**
 * Gestisce la chiamata HTTP GET all'url "/gestione-aula-edit".
 * Richiesta inviata tramite l'id dell'oggetto aula.
 * Restituisce la view con il form di modifica.
 * @param	id aula Aula
 * @param	model Model
 * @return	/gestione-aula-edit
 */

    @GetMapping("/gestione-aula-edit/{id}")
    public String getAulaEdit(@PathVariable String id, Model model) {
        Aula aula = aulaService.getAulaById(Long.parseLong(id));
        model.addAttribute("aula", aula);
        //System.out.println(aula);
        return "gestione-aula-edit";
    }

/**
 * Gestisce la chiamata HTTP POST all'url "/gestione-aula-edit".
 * Ricevuti i dati dal form, l'oggetto è aggiornato.
 * Restituisce la view della pagina iniziale di gestione.
 * @param	aula Aula object
 * @return	/gestione-aula
 */
	@PostMapping("/gestione-aula-edit")
    public String getUpdateAula(@ModelAttribute Aula aula) {
		//System.out.println(aula);
        aulaService.updateAula(aula);
        return "redirect:/gestione-aula";
    }

/**
 * Gestisce la chiamata HTTP GET all'url "/gestione-aula-delete".
 * Condizione: 
 * L'oggetto aula è eliminato se non ha nessun oggetto utente assegnato.
 * Se l'oggetto aula ha oggetti utenti assegnati visualizza un alert.
 * La view rimane sulla pagina iniziale di gestione.
 * @param	id aula Aula
 * @return	/gestione-aula
 */
	@GetMapping("/gestione-aula-delete/{id}")
	public String deleteAula(@PathVariable String id, Model model) {
		Aula aula = aulaService.getAulaById(Long.parseLong(id));
	    if (aula.getUtenti().isEmpty()) {
	    	aulaService.deleteAulaById(Long.parseLong(id));
	    	return "redirect:/gestione-aula";
	    } else {
	    	model.addAttribute("alert", true);
	    }
	    return "gestione-aula";
	}
	
/**
 * Gestisce la chiamata HTTP GET all'url "/gestione-aula-add".
 * Restituisce la view con il form di creazione.
 * @param	model Model
 * @return	/gestione-aula-add
 */
	@GetMapping("/gestione-aula-add")
	public String addAula(Model model) {
	    Aula aula = new Aula();
	    model.addAttribute("aula", aula);
	    return "gestione-aula-add";
	}
	
/**
 * Gestisce la chiamata HTTP POST all'url "/gestione-aula-add".
 * Ricevuti i dati dal form, l'oggetto aula è stato creato.
 * Restituisce la view della pagina iniziale di gestione.
 * @param	aula Aula object
 * @return	/gestione-aula
 */
	@PostMapping("/gestione-aula-add")
    public String getAula(@ModelAttribute Aula aula) {
		//System.out.println(aula);
        aulaService.addAula(aula);
        return "redirect:/gestione-aula";
    }
	
/**
 * Gestisce la chiamata HTTP GET all'url "/gestione-aula-studenti".
 * Restituisce la view con l'elenco di tutti gli oggetti Utente associati alla tabella aula.
 * @param	id aula Aula
 * @param	model Model
 * @return	/gestione-aula-studenti
 */
	@GetMapping("/gestione-aula-studenti/{id}")
	public String getUtentiByAula(@PathVariable Long id, Model model) {
	    List<Utente> utenti = aulaService.findUtenteByAulaId(id); 
	    List<Utente> utenti_null = aulaService.findUtenteByAulaId(null); 
	    model.addAttribute("utenti", utenti); 
	    model.addAttribute("utenti_null", utenti_null); 
	    model.addAttribute("aula", id); 
	    
	    return "gestione-aula-studenti";
	}
	
/**
 * Gestisce la chiamata HTTP POST all'url "/gestione-aula-studenti".
 * Ricevuti i dati dalla select, l'oggetto utente è stato aggiunto all'elenco.
 * Restituisce la view della pagina di gestione degli utenti.
 * @param	utente Utente object
 * @return	/gestione-aula-studenti
 */
	@PostMapping("/gestione-aula-studenti/{id}")
	public String addUtenteToLista(
						@PathVariable Long id, @RequestParam(name = "nuovoStudente") 
						Long id_utente, Model model) {
	    Aula aula = aulaService.getAulaById(id);
	    utenteService.assegnaAula(id_utente, aula);
	    return "redirect:/gestione-aula-studenti/" + id;
	}

/**
 * Gestisce la chiamata HTTP GET all'url "/gestione-aula-quiz".
 * Restituisce la view con l'elenco di tutti gli oggetti Quiz associati alla tabella aula.
 * @param	id aula Aula
 * @param	model Model
 * @return	/gestione-aula-quiz
 */
	@GetMapping("/gestione-aula-quiz/{id}")
	public String getQuizByAula(@PathVariable Long id, Model model) {
	    Set<Quiz> quizzes = aulaService.findQuizByAulaId(id); 
	    model.addAttribute("quizzes", quizzes);
	    return "gestione-aula-quiz";
	}
    
}