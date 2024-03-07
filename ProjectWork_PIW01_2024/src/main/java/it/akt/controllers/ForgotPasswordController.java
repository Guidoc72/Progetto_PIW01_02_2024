package it.akt.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.akt.models.Utente;
import it.akt.services.CustomerNotFoundException;
import it.akt.services.UtenteService;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


import net.bytebuddy.utility.RandomString;

/**
 * Controller per ricupero di password
 * 
 * @author Rodrigo
 */
@Controller
public class ForgotPasswordController {


	@Autowired
	private UtenteService utenteService;
	
	@Autowired(required=true)
	private JavaMailSender mailSender;
	
	public ForgotPasswordController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}
	
	
	@GetMapping("/forgottenPassword")
	public String getPassDimenticata(Model model) {
		model.addAttribute("utente", new Utente());
		
		
		return "forgottenPassword";
	}
	
	@PostMapping("/forgottenPass")
	public String passDimenticata(@ModelAttribute Utente utente, Model model, HttpServletRequest request) {
//		public String passDimenticata(Model model, HttpServletRequest request) {
	

		
		String email = utente.getEmail();
//		String email = "email";
		
	
		String token = RandomString.make(45);
		
		try {
			utenteService.updatePasswordToken(token, email);
			
			//Creo una classe Utility e creo il link con il token per resetare la password
			
			String resetPasswordLink = Utility.getSiteURL(request) + "/resetPassword?token=" + token;
			
			sendEmail(email, resetPasswordLink);
			
			
			
			//con questo deve confermare il link inviato
			model.addAttribute("message", "Troverà un link per impostare una nuova password nella sua email");
			
		} catch (CustomerNotFoundException e) {
			model.addAttribute("error", e.getMessage());
			
		} catch (UnsupportedEncodingException | MessagingException e) {
		
			model.addAttribute("error", "Error while sending email.");
		} 
		
		return "forgottenPassword";
	}


	private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("aktproject2024@outlook.it", "projectworks");
		
		helper.setTo(email);
		
		String subject = "Con questo link può resetare la password";
		
		String content = "<p> Ciao, </p>"
				+ "<p>Hai chiesto di resetare la sua password</p>"
				+ "<p>Chicca il link sotto per cambiare la sua password</p>"
				+ "<p><b><a href=\"" + resetPasswordLink + "\"> Resetta la password </a> </p>"
				+ "<p>Ignora questa email si ricorda la password o no ha fatto la richiesta per cambiare.</p>";
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
	}
	
	@GetMapping("/resetPassword")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		Utente utente = utenteService.getByResetPasswordToken(token);
	
		
		if(utente == null) {
			model.addAttribute("title", "Cambia la tua password");
			model.addAttribute("message", "Token invalido");
			
			return "resetPasswordForm"; //Ritorna a pagina 404 not found
		}
		model.addAttribute("utente", utente);
		
		return "resetPasswordForm";
	}
	
	
	@PostMapping("/resetPassword")
	public String processResetPassword(@ModelAttribute("utente") Utente utente,@ModelAttribute("confirmPass") String confirmPass, Model model, RedirectAttributes redirectAttributes) {
		
		Utente utenteDaForm = utenteService.getByResetPasswordToken(utente.getPasswordToken());
		
		String password = utente.getPassword();
		
		/**
		 * Controllo che le due input di password siano uguali, in caso contrario mando un messaggio altrimenti si 
		 * procede a salvare la nuova password e cancello il token
		 * 
		 * @Autor Rodrigo
		 */
		
		if(!password.equals(confirmPass)) {
			model.addAttribute("message", "Le password non coincidono");
			return "resetPasswordForm";
		
		} else {
		
		
		utenteService.updatePassword(utenteDaForm, password);
	
		model.addAttribute("message", "La password è stato aggiornato correttamente");
		} 
			
		redirectAttributes.addFlashAttribute("showToast3", true);
	    // Stampa un messaggio di debug per verificare l'invio del secondo toast.
		return "redirect:/login";
	}

}

