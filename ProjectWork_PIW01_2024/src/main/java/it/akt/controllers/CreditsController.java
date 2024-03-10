package it.akt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreditsController {
	
	@GetMapping("/credits")
    public String redirectToCredits() {
        return "/credits";
    }

}
