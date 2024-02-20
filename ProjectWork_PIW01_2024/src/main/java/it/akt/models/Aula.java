package it.akt.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "aula")
public class Aula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "risultato_has_aula", 
				joinColumns = @JoinColumn(name = "aula_id"), 
				inverseJoinColumns = @JoinColumn(name = "risultato_id", 
				referencedColumnName = "id"))
	private Set<Risultato> risultati = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "utente_has_aula", 
				joinColumns = @JoinColumn(name = "aula_id"), 
				inverseJoinColumns = @JoinColumn(name = "utente_id", 
				referencedColumnName = "id"))
	private Set<Utente> utenti = new HashSet<>();

	public Aula() {
	}

	// costruttore senza Set
	public Aula(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	// costruttore con Set
	public Aula(Long id, String nome, Set<Risultato> risultati, Set<Utente> utenti) {
		this.id = id;
		this.nome = nome;
		this.risultati = risultati;
		this.utenti = utenti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Risultato> getRisultati() {
		return risultati;
	}

	public void setRisultati(Set<Risultato> risultati) {
		this.risultati = risultati;
	}

	public Set<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(Set<Utente> utenti) {
		this.utenti = utenti;
	}

	@Override
	public String toString() {
		return "Aula [id=" + id + ", nome=" + nome + "]";
	}

}
