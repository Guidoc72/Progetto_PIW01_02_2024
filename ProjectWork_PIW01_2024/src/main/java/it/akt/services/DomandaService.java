package it.akt.services;

import java.util.List;

import it.akt.models.Domanda;
import it.akt.repositories.DomandaRepository;

public class DomandaService {
	
	private DomandaRepository domandaRepository;
	
	public DomandaService (DomandaRepository domandaRepository) {
		this.domandaRepository=domandaRepository;
	}
	
	public void addDomanda (Domanda domanda) {
		domandaRepository.save(domanda);		
	}
	
	public void deleteDomandaById (Long id) {
		domandaRepository.deleteById(id);
	}
	
	public void updateDomanda (Domanda domanda) {
		domandaRepository.save(domanda);
	}
	
	public Domanda findDomandaById (Long id) {
		return domandaRepository.findById(id).orElseThrow();
	}
	
	public List<Domanda> findAllDomanda () {
		return domandaRepository.findAll();
	}
	
	public List<Domanda>findDomandaByTema (Tema tema) {
		return domandaRepository.findDomandaByTema(tema);
	}

}
