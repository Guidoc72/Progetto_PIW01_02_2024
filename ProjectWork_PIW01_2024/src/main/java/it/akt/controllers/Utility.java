package it.akt.controllers;

import jakarta.servlet.http.HttpServletRequest;

/**
 * serve per mandare la mail di ricupero password
 * @author Rodrigo
 */

public class Utility {
	
	
	/**
	 * con questa classe si crea l'URL per reimpostare la password, arriva all'utente un link nella mail 
	 * @param request
	 * @return
	 * @autor Rodrigo
	 */
	
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		
		return siteURL.replace(request.getServletPath(), "");
	}

}
