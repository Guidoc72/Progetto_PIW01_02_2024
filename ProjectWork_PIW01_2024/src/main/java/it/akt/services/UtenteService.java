package it.akt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.akt.models.Aula;
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
		return utenteRepository.findAll();
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
	 * Recupera un utente cercandolo per Email. 
	 * @param email String
	 * @return Utente object
	 */
	public Utente getUtenteByEmail (String email) {
		return utenteRepository.findByEmail(email);
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

	public List<Utente> findAllByRuolo(int ruolo){
		return utenteRepository.findAllByRuolo(ruolo);
	}
	
	/**
	 * Assegna un utente ad una tabella aula.
	 * @param 	id utente Utente
	 * @param 	aula Aula object
	 * @return 	utente Utente object
	 */
	public Utente assegnaAula(Long id, Aula aula) {
		Utente utente = utenteRepository.findById(id).orElseThrow(() -> 
			new RuntimeException(String.format("Non esiste nessuna classe con id: %d", id)));
		utente.getAule().add(aula);
		System.out.println(utente.getAule().size());
		return utenteRepository.save(utente);
	}
	
}