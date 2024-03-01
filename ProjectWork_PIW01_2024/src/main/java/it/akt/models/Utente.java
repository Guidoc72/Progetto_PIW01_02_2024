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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table (name = "utente")
public class Utente {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "[a-zA-Z\\s]{3,30}", message = "La stringa deve contenere almeno 3 caratteri diversi da numeri e simboli")
	@NotEmpty(message = "Il campo non può essere vuoto")	
	@Column(name="nome", nullable = false, length = 40)
	private String nome;
	
	@Pattern(regexp = "[a-zA-Z\\s]{3,30}", message = "La stringa deve contenere almeno 3 caratteri diversi da numeri e simboli")
	@NotEmpty(message = "Il campo non può essere vuoto")	
	@Column(name="cognome", nullable = false, length = 40)
	private String cognome;
	
	@Email(message = "L'indirizzo email non è valido", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
	@Column(name = "email", nullable = false, length = 40)
	private String email;
	
	@Column(name = "telefono", nullable = true, length = 15)
	private String telefono;
	
	@Column(name = "ruolo", nullable = false, length = 15)
	private int ruolo;
	
	@Column(name = "abilitato")
	private boolean isEnabled;
	
	@NotEmpty(message = "Il campo non può essere vuoto")
	@Column(name = "password")
	private String password;
	
	@Column(name = "password_token")
	private String passwordToken;
	
	@Transient
	private String codiceDocente;
	
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
	public Utente(Long id, String nome, String cognome, String email, 
			String telefono, int ruolo, boolean isEnabled, String password, String passwordToken, String codiceDocente, Set<Quiz> quiz, Set<Aula> aule) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
		this.ruolo = ruolo;
		this.isEnabled = isEnabled;
		this.password = password;
		this.passwordToken = passwordToken;
		this.codiceDocente= codiceDocente;
		this.quiz = quiz;
		this.aule = aule;
	}
	
	//costruttore senza Set
	public Utente(Long id, String nome, String cognome, String email, 
			String telefono, int ruolo, boolean isEnabled, String password, String passwordToken, String codiceDocente) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
		this.ruolo = ruolo;
		this.isEnabled = isEnabled;
		this.password = password;
		this.passwordToken = passwordToken;
		this.codiceDocente = codiceDocente;
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordToken() {
		return  passwordToken;
	}
	public void setPasswordToken(String passwordToken) {
		this.passwordToken = passwordToken;
	}
	
	public String getCodiceDocente() {
		return codiceDocente;
	}
	
	public void setCodiceDocente(String codiceDocente) {
		this.codiceDocente = codiceDocente;
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
		return "Utente [id=" + id + ", nome=" + nome + ", codiceDocente=" + codiceDocente + ", cognome=" + cognome + ", email=" + email + ", telefono="
				+ telefono + ", ruolo=" + ruolo + ", isEnabled=" + isEnabled + "]";
	}



}