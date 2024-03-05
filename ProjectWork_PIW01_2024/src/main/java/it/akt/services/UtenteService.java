package it.akt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.akt.models.Aula;
import it.akt.models.Utente;
import it.akt.repositories.UtenteRepository;

@Service
public class UtenteService {
	
	
	private UtenteRepository utenteRepository;
	
	
	
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	


	
	

	@Autowired
	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
		//this.passwordEncoder = passwordEncoder;
	}
	
	
	
	
	
	/**
	 * Aggiunge un utente al database e ritorna una stringa contenente la l'esito del salvataggio
	 * @param utente Utente object
	 * @return restituisce l'object salvato
	 */
	
	public Utente createNewUtente(Utente utente) {
		try {
			Utente emailInserito = utenteRepository.findByEmail(utente.getEmail());
			
			if(emailInserito != null) {
				boolean isExist = true;
				
				return null;
			}
			
			
			
			
			//passwordCriptata
			String encriptedPass = passwordEncoder.encode(utente.getPassword());
			utente.setPassword(encriptedPass);
			return utenteRepository.save(utente);
		} catch (Exception e){
			return null;
			
		}
	}
	
	/**
	 * Controlla se l'utente è presente nel Database e restituisce un booleano come risultato
	 * @param formUser User object 
	 * @return boolean
	 */
		public boolean authorizedUser(Utente formUtente) {
			Utente searchResult = utenteRepository.findByEmail(formUtente.getEmail());
			
			
			if (searchResult != null ) {
				Utente dbUser = searchResult;
				String passEncode = (formUtente.getPassword());
				String passDb = searchResult.getPassword();
				

				
				//se l'oggetto non viene trovata lancia NoSuchElementException
				
					if (passwordEncoder.matches(passEncode, searchResult.getPassword())) {
					
					return true;
				} else {
					return false;
				}
			} else 
				return false;
		}
		
	
	/**
	 * Ritorna la lista di tutti gli utenti nel database
	 * @return List<Utente>
	 */
	public List<Utente> getUtenteList(){
		return (List<Utente>) utenteRepository.findAll();
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
	 * Recupera un token utente con passworkToken quando l'utente vuole resetare password. 
	 * @param email String
	 * @return Utente object
	 * @throws CustomerNotFoundException 
	 */
	
	public void updatePasswordToken (String token, String email) throws CustomerNotFoundException {
		Utente utente = utenteRepository.findByEmail(email);
		
		if(utente != null) {
			utente.setPasswordToken(token);
			utenteRepository.save(utente);
		}else {
			throw new CustomerNotFoundException("Non esiste nessun utente con la seguente email:  " + email);
		}
	}
	
	public Utente getByResetPasswordToken(String resetPasswordToken) {
		return utenteRepository.findBypasswordToken(resetPasswordToken);
	}
	
	public void updatePassword(Utente utente, String newPassword) {
		//TOGLIERE COMMENTO QUANDO FUNZIONA BCRIPT
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encoderPassword = passwordEncoder.encode(newPassword);
		
		utente.setPassword(encoderPassword);
	
		utente.setPasswordToken(null);
		
		utenteRepository.save(utente);
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
		
		return utenteRepository.save(utente);
	}
	
}


	