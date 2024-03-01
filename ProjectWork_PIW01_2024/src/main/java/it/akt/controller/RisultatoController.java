package it.akt.controller;


import it.akt.models.Domanda;
import it.akt.models.Risultato;
import it.akt.services.DomandaService;
import it.akt.services.RisultatoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("class/results")
public class RisultatoController {
    private final RisultatoService risultatoService;
    private final DomandaService domandaService;
    RisultatoController(RisultatoService risultatoService, DomandaService domandaService) {
        this.risultatoService = risultatoService;
        this.domandaService = domandaService;
    }

    @GetMapping("/{id}/**")
    public String notFound(@PathVariable String id) {
        return "404";
    }
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

        if(splitted.length == 2) {
            List<Risultato> r = this.risultatoService.findAllByQuizId(idQuiz,idClass);
            Set<Domanda> d = this.domandaService.getAllDomandeByQuizId(idQuiz);

            if(r.isEmpty())
                return "404";
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

        Risultato r = this.risultatoService.findByUtenteIdAndQuizId(idUser,idQuiz);
        Set<Domanda> d = this.domandaService.getAllDomandeByQuizId(idQuiz);
        if(r == null)
            return "404";
        System.out.println(r.getQuiz().getId() + " " + r.getPunteggio());
        model.addAttribute("Risultato", r);
        model.addAttribute("ListaDomande", d);
        return "result";
    }
}
