package it.akt.controllers;


import it.akt.models.Aula;
import it.akt.models.Domanda;
import it.akt.models.Quiz;
import it.akt.models.Risultato;
import it.akt.models.Utente;
import it.akt.services.AulaService;
import it.akt.services.DomandaService;
import it.akt.services.QuizService;
import it.akt.services.RisultatoService;
import it.akt.services.UtenteService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/results")
public class RisultatoController {
    private final RisultatoService risultatoService;
    private final DomandaService domandaService;
    private final UtenteService utenteService;
    private final QuizService quizService;
    private AulaService aulaService;
    RisultatoController(RisultatoService risultatoService, DomandaService domandaService, UtenteService utenteService, QuizService quizService,AulaService aulaService) {
        this.risultatoService = risultatoService;
        this.domandaService = domandaService;
        this.utenteService = utenteService;
        this.quizService = quizService;
        this.aulaService = aulaService;
    }

//    @GetMapping("/{id}/**")
//    public String notFound(@PathVariable String id) {
//        return "404";
//    }
    @GetMapping("/{id}")
    public String getResult(@PathVariable String id, Model model) {
        String splitted[] = id.split("_");
        
        Long idClass = Long.parseLong(splitted[0]);
        Long idQuiz = Optional.of(splitted)
                .filter(value -> value.length > 1)
                .map(value -> {
                    try {
                        return Long.parseLong(value[1]);
                    } catch (NumberFormatException e) {
                        return -1L;
                    }
                })
                .orElse(-1L);
        Long idUser = Optional.of(splitted)
                .filter(value -> value.length > 2)
                .map(value -> {
                    try {
                        return Long.parseLong(value[2]);
                    } catch (NumberFormatException e) {
                        return -1L;
                    }
                })
                .orElse(-1L);
        
        System.out.println(idClass);

        if(splitted.length == 2) {
            List<Risultato> r = this.risultatoService.findAllByQuizId(idQuiz,idClass);
            Set<Domanda> d = this.domandaService.getAllDomandeByQuizId(idQuiz);

            if(r.isEmpty())
                return "error";
            for (Risultato t: r) {
                System.out.println(t.getQuiz().getId() + " " + t.getUtente().getId());
            }
            for (Domanda t: d) {
                System.out.println(t.getQuesito() + " " + t.getId());
            }
            model.addAttribute("ListaRisultati", r);
            model.addAttribute("ListaDomande", d);
            return "results";
        }
        
        
        Aula aula = aulaService.findByUtentiId(idUser);
        
         if(aula == null || aula.getId() != idClass) return "error";

        Risultato r = this.risultatoService.findByUtenteIdAndQuizId(idUser,idQuiz);
        Set<Domanda> d = this.domandaService.getAllDomandeByQuizId(idQuiz);
        if(r == null)
            return "error";
        model.addAttribute("Risultato", r);
        model.addAttribute("ListaDomande", d);
        return "result";
    }
    
    @PostMapping("/save_risposta")
    public String saveRisposta(@ModelAttribute("risultato") Risultato risultato, @ModelAttribute("quiz") Quiz quiz, @ModelAttribute("idUtente") String idUtente) throws Exception {
    	
    	Aula aula = aulaService.findByUtentiId(Long.parseLong(idUtente));
    	Quiz newQuiz = quizService.findQuizById(quiz.getId());
    	
    	risultato.setQuiz(newQuiz);
        
        Utente utente = utenteService.getUtenteById(Long.parseLong(idUtente));
        risultato.setUtente(utente);
        Set<Domanda> d = this.domandaService.getAllDomandeByQuizId(quiz.getId());
        Domanda[] dom = d.toArray(new Domanda[0]);
        int punteggio = 0;
        for (int i = 0; i < d.size(); i++) {
            if(risultato.getRisposta(i) == dom[i].getRisposta_giusta())
                punteggio++;
        }
        
        System.out.println("risposte:");
        for(int i = 0; i<10; i++) {
        	System.out.println(risultato.getRisposta(i));
        }
        
        risultato.setPunteggio(punteggio);
        
        System.out.println("Punteggio:");
        System.out.println(punteggio);

        risultatoService.addRisultato(risultato);

        return "redirect:/results/" + aula.getId() + "_" + quiz.getId() + "_" + idUtente;
    }
}
