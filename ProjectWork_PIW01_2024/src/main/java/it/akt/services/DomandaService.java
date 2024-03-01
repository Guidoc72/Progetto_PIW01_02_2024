package it.akt.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import it.akt.models.Domanda;
import it.akt.models.TemaQuiz;
import it.akt.repositories.DomandaRepository;
@Service
public class DomandaService {
	
	private DomandaRepository domandaRepository;
	
	public DomandaService (DomandaRepository domandaRepository) {
		this.domandaRepository=domandaRepository;
	}
	/**
	 * Aggiunge una domanda al database e ritorna come oggetto la domanda creata
	 * @param domanda Domanda object
	 * @return oggetto domanda
	 */
	public Domanda addDomanda (Domanda domanda) {
		return domandaRepository.save(domanda);		
	}

	/**
	 * Elimina una domanda dal database 
	 * @param id Long
	 */	
	public void deleteDomandaById (Long id) {
		Domanda domanda = domandaRepository.findDomandaById(id);		
		domandaRepository.delete(domanda);
	}
	
	/**
	 * Aggiorna una domanda dal database e ritorna come oggetto la domanda aggiornata
	 * @param domanda Domanda object
	 * @return oggetto domanda
	 */
	public Domanda updateDomanda (Domanda domanda) {
		return domandaRepository.save(domanda);
	}
	
	/**
	 * Cerca una domanda tramite l'id dal database e ritorna come oggetto la domanda trovata
	 * @param id Long 
	 * @return oggetto domanda
	 */
	public Domanda findDomandaById (Long id) {
		return domandaRepository.findById(id).orElseThrow();
	}
	
	/**
	 * Cerca le domande presenti nel database e ritorna la lista delle domande trovate 
	 * @return lista domande
	 */
	public List<Domanda> findAllDomanda () {
		return domandaRepository.findAll();
	}
	
	/**
	 * Cerca le domande presenti nel database in funione del tema e ritorna la lista delle domande trovate
	 * @param  tema Tema
	 * @return lista domande
	 */
	public Set<Domanda>findDomandaByTema (TemaQuiz tema) {
		return domandaRepository.findDomandaByTema(tema);
	}
	
	public Set<Domanda>findDomandaByTemaId (Long id) {
		return domandaRepository.findAllByTemaId(id);
	}
	public Set<Domanda> getAllDomandeByQuizId(Long idQuiz) {
		// TODO Auto-generated method stub
		return domandaRepository.findAllByQuizId(idQuiz);
	}

}
