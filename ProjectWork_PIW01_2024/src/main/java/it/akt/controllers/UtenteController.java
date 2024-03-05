package it.akt.controllers;

import java.util.List;
import java.util.Set;


import it.akt.models.Aula;
import it.akt.models.Quiz;
import it.akt.models.Risultato;
import it.akt.services.AulaService;
import it.akt.services.QuizService;
import it.akt.services.RisultatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.akt.models.Utente;
import it.akt.services.UtenteService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes(names = {"nomeUtente", "cognomeUtente"})
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;

	@Autowired
	private RisultatoService risultatoService;
	@Autowired
	private QuizService quizService;
	@Autowired
	private AulaService aulaService;

	public UtenteController() {
	}


	public UtenteController(UtenteService utenteService, RisultatoService risultatoService, QuizService quizService) {
		this.utenteService = utenteService;
		this.risultatoService = risultatoService;
		this.quizService = quizService;
	}

	@GetMapping("/registrationdgsdfgadgdf")
    public String getAllUsers(Model model) {
        List<Utente> allUsers = utenteService.getUtenteList();
        model.addAttribute("utente", allUsers);
        
        
        return "login";
    }
    

	/**
	 * Controller che cattura i dati dal form di Login, controlla se l'utente è presente nel DB e se la password corrisponde a quella salvata	
	 * @param formUser	User
	 * @param session	HttpSession
	 * @return String:	pagina interna se l'accesso funziona,<br>
	 * 					rimanda alla pagina di Login se le credenziali sono errate
	 */
		@GetMapping(path = {"/", "/index", "/login"})
		public String getLogin(Model model, HttpSession session) {
			String nomeUtente = (String) session.getAttribute("utente");
			model.addAttribute("utente", new Utente());
			
			//controllo toast modifica psw
			if (model.containsAttribute("showToast3")) {
	        	model.addAttribute("showToast3", true);
	        }
			
			return "login";
		
		}
		
		@PostMapping("/login")
		public String login(@ModelAttribute("utente") Utente utente, Model model, HttpSession session) {
			//System.out.println("### Utente: " + utente);
			String username = utente.getEmail();
//			session.setAttribute("utente", username);
			String nomeUtente = (String) session.getAttribute("utente");
			
		
			
			if(utenteService.authorizedUser(utente)) {
				//session.setAttribute("utenteIsAutothenticated", username);
				Utente dbUtente = utenteService.getUtenteByEmail(utente.getEmail());
				session.setAttribute("nomeUtente", dbUtente.getNome());
				session.setAttribute("cognomeUtente", dbUtente.getCognome());
				session.setAttribute("ruoloUtente", dbUtente.getRuolo());
				session.setAttribute("idUtente", dbUtente.getId());
				
				
				dbUtente.setPassword("");
				model.addAttribute("utente", dbUtente);
				if(dbUtente.getRuolo() == 1 || dbUtente.getRuolo() == 0) { 
					//session.setAttribute("utenteIsDocente", 1);
				
					return "redirect:home";
				}
				return "redirect:login";
			}else
			model.addAttribute("message", "Utente o password errata!");
			return "login";
		
		}

	
		
		@GetMapping("/home")
		public String home(@ModelAttribute Utente utente, Model model, HttpSession session) {
			model.addAttribute("utente", utente);


			Long idUtente = (Long) session.getAttribute("idUtente");
			List<Risultato> r = risultatoService.findAllByUtenteId(idUtente);
			Set<Quiz> q = quizService.findQuizByUtenteId(idUtente);
			Aula a = aulaService.findByUtentiId(idUtente);
			model.addAttribute("quizzes", q);
			model.addAttribute("risultati", r);
			model.addAttribute("aula",a);


			 //Utente utente =(Utente) session.getAttribute("utenteIsAutothenticated");
			
			
			
			return "home";
		
		}
		
		@GetMapping("/logout")
		public String logout(Model model, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
			
			String nomeUtente = (String) session.getAttribute("utente");
			model.addAttribute("utente", new Utente());
			
			Cookie [] cookies = req.getCookies();
	    	for(Cookie c : cookies) {
	    		if(c.getName().equals("JSESSIONID")) {
	    			
	    			
	    			c.setValue(null);
	    			c.setMaxAge(0);
	    			c.setPath("/");
	    			res.addCookie(c);
	    			
	    		}
	    	}
	    	session.invalidate();
			return "redirect:/login";
		}
		
		
	
	

}