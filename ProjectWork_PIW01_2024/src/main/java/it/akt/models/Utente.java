package it.akt.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table (name = "utente")
public class Utente {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^(?<!\\s)[a-zA-Z]{3, 30}?$", 
			message = "Questo campo deve contenere almeno 3 caratteri")
	@NotEmpty(message = "Il campo non può essere vuto")	
	@Column(name="nome", nullable = false, length = 40)
	private String nome;
	
	@Pattern(regexp = "^(?<!\\s)[a-zA-Z]{3, 30}?$", 
			message = "Questo campo deve contenere almeno 3 caratteri")
	@NotEmpty(message = "Il campo non può essere vuto")	
	@Column(name="cognome", nullable = false, length = 40)
	private String cognome;
	
	@Email //controlla che il formato sia Email valido
	@Column(name = "email", nullable = false, length = 40)
	private String email;
	
	@Pattern(regexp = "^[a-zA-Z][0-9]{8, }$", 
			message = "La password deve contenere almeno 8 caratteri tra cui una maiuscola e un numero")
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "telefono", nullable = true, length = 15)
	private String telefono;
	
	@NotEmpty
	@Column(name = "ruolo", nullable = false, length = 15)
	private int ruolo;
	
	@NotEmpty
	@Column(name = "abilitato", nullable = false)
	private boolean isEnabled;
	
	@ManyToMany(mappedBy = "utenti")	
	Set<Quiz> quiz = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "utente_has_aula",
				joinColumns = @JoinColumn(name = "utente_id"),
				inverseJoinColumns = @JoinColumn(name = "aula_id")
				)
	private Set<Aula> aule = new HashSet<>();
		
	public Utente() {}

	//costruttore con Set
	public Utente(Long id, String nome, String cognome, String email, String password,
			String telefono, int ruolo, boolean isEnabled, Set<Quiz> quiz, Set<Aula> aule) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.telefono = telefono;
		this.ruolo = ruolo;
		this.isEnabled = isEnabled;
		this.quiz = quiz;
		this.aule = aule;
	}
	
	//costruttore senza Set
	public Utente(Long id, String nome, String cognome, String email, String password,
			String telefono, int ruolo, boolean isEnabled) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.telefono = telefono;
		this.ruolo = ruolo;
		this.isEnabled = isEnabled;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getRuolo() {
		return ruolo;
	}

	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(Set<Quiz> quiz) {
		this.quiz = quiz;
	}

	public Set<Aula> getAule() {
		return aule;
	}

	public void setAule(Set<Aula> aule) {
		this.aule = aule;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", telefono="
				+ telefono + ", ruolo=" + ruolo + ", isEnabled=" + isEnabled + "]";
	}

}