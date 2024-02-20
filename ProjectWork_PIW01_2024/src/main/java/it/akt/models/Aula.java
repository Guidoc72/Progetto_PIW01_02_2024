package it.akt.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "aula")
public class Aula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "nome", nullable = false)
	private String nome;
	
	//vedi classe Quiz
	@ManyToMany(mappedBy = "aule")
	private Set<Quiz> quiz = new HashSet<>();
	
	//vedi classe Utente
	@ManyToMany(mappedBy = "utenti")
	private Set<Utente> utenti = new HashSet<>();
	
	public Aula() {
	}

	// costruttore senza Set
	public Aula(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	// costruttore con Set
	public Aula(Long id, String nome, Set<Quiz> quiz, Set<Utente> utenti) {
		this.id = id;
		this.nome = nome;
		this.quiz = quiz;
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

	public Set<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(Set<Quiz> quiz) {
		this.quiz = quiz;
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
