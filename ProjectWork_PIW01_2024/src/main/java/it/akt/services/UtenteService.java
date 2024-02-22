package it.akt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.akt.models.Utente;
import it.akt.repositories.UtenteRepository;


@Service
public class UtenteService {
	
	private UtenteRepository utenteRepository;
	
	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}
	
	/**
	 * Aggiunge un utente al database e ritorna una stringa contenente la l'esito del salvataggio
	 * @param utente Utente object
	 * @return restituisce l'object salvato
	 */
	
	public Utente createNewUtente(Utente utente) {
		return utenteRepository.save(utente);
		
	}
	
	/**
	 * Ritorna la lista di tutti gli utenti nel database
	 * @return List<Utente>
	 */
	
	public List<Utente> getUtenteList(){
		return (java.util.List<Utente>) utenteRepository.findAll();
	}
	
	
	/**
	 * Recupera un utente cercandolo per ID. Lancia {@code NoSuchElementException} se non trova alcun elemento
	 * @param id Long
	 * @throws NoSuchElementException se non trova elementi
	 * @return Utente object
	 */
	public Utente getUtenteById (Long id) {
		return utenteRepository.findById(id).orElseThrow();
	}
	
	/**
	 * Esegue l'aggiornamento di un oggetto "Utente". 
	 * Metodo {@code void} che non ritorna nulla
	 * @param utente Utente
	 */
	
	public void updateUtente(Utente utente) {
		utenteRepository.save(utente);
	}
	
	/**
	 * Elimina l'utente di cui riceve l'ID in ingresso come parametro, senza effettuare controlli, né chiedere conferma 
	 * Metodo {@code void} che non ritorna nulla
	 * @param id Long
	 */
	public void deleteUtenteById (Long id) {
		Utente utenteToDelete = utenteRepository.findById(id).orElseThrow();
		utenteRepository.delete(utenteToDelete);
	}

}