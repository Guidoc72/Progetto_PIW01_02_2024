package it.akt.services;

/**
 * Classe per intercettare Exceptions quando un utente 
 * chiede ricupero password 
 * 
 * @author Rodrigo
 */

public class CustomerNotFoundException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String message) {
		super(message);
		
	}

	

}