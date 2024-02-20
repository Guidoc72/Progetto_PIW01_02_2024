package it.akt.models;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
@Table (name ="utente", schema="quizDB")
public class Utente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "[a-zA-Z\\s]{3,30}", message = "La stringa deve contenere almeno 3 caratteri diversi da numeri e simboli")
	@NotEmpty(message = "Il campo non può essere vuto")	
	@Column(name="nome", nullable = false, length = 40)
	private String nome;
	
	@Pattern(regexp = "[a-zA-Z\\s]{3,30}", message = "La stringa deve contenere almeno 3 caratteri diversi da numeri e simboli")
	@NotEmpty(message = "Il campo non può essere vuto")	
	@Column(name="nome", nullable = false, length = 40)
	private String cognome;
	
	@Email //controlla che il formato sia Email valido
	@Column(nullable = false, length = 40)
	private String email;
	
	@Column(nullable = true, length = 15)
	private String telefono;
	
	@Column(nullable = false, length = 15)
	private int role;
	
	@Column
	private boolean isEnabled;
	
	 @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(name = "quiz_has_utente",		
	            joinColumns = @JoinColumn(name = "user_id"),  
	            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	 
	 Set<Quiz> quizUtente = new HashSet<>();

	
	public Utente() {}
	
	public Utente(Long id, String nome, String cognome, String email, String telefono, int role, boolean isEnabled) {
		this.id=id;
		this.nome=nome;
		this.cognome=cognome;
		this.email=email;
		this.telefono=telefono;
		this.role=role;
		this.isEnabled=isEnabled;
		
		
	}
	
	
	public Utente(String nome, String cognome, String email, String telefono, int role, boolean isEnabled) {
	
		this.nome=nome;
		this.cognome=cognome;
		this.email=email;
		this.telefono=telefono;
		this.role=role;
		this.isEnabled=isEnabled;
		
		
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
	
	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", telefono="
				+ telefono + ", role=" + role + ", isEnabled=" + isEnabled + "]";
	}
	
	

}