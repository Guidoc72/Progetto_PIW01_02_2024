package it.akt.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tema")

/**
 * Rappresenta un tema
 * @author Federico 
 */
public class TemaQuiz {

/**
 * L'ID univoco del tema.
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/**
 * Il nome del tema.
 */
    @Column(name = "nome", nullable = false)
    @NotEmpty
    private String nome;

/**
 * Relazione uno a molti con gli oggetti Quiz associati a questo tema.
 */
    @OneToMany
    private Set<Quiz> quiz;

/**
 * Relazione uno-a-molti con le domande associate a questo tema.
 */
    @OneToMany
    private Set<Domanda> domande;
    
/**
 * Costruttore per creare un oggetto istanza della classe `TemaQuiz`.
 * 
 * @param id     L'ID univoco del tema.
 * @param nome   Il nome del tema.
 * @param quiz   Insieme di oggetti `Quiz` associati a questo tema.
 * @param domande Insieme di oggetti `Domanda` associati a questo tema.
 */
    public TemaQuiz(Long id, @NotEmpty String nome, Set<Quiz> quiz, Set<Domanda> domande) {
    	this.id = id;
    	this.nome = nome;
    	this.quiz = quiz;
    	this.domande = domande;
    }
    
/**
 * Costruttore vuoto (senza parametri) per la classe `TemaQuiz`.
 */
	public TemaQuiz() {}

/**
 * Restituisce l'ID del tema.
 *
 * @return L'ID del tema.
 */
	public Long getId() {
		return id;
	}

/**
 * Imposta l'ID del tema.
 *
 * @param id L'ID da assegnare al tema.
 */
	public void setId(Long id) {
		this.id = id;
	}
	
/**
 * Restituisce il nome del tema.
 *
 * @return Il nome del tema.
 */
	public String getNome() {
		return nome;
	}

/**
 * Imposta il nome del tema.
 *
 * @param nome Il nome da assegnare al tema.
 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
/**
 * Restituisce l'insieme di oggetti `Quiz` associati a questo tema.
 *
 * @return L'insieme di oggetti `Quiz`.
 */
	public Set<Quiz> getQuiz() {
		return quiz;
	}

/**
 * Imposta l'insieme di oggetti `Quiz` associati a questo tema.
 *
 * @param quiz L'insieme di oggetti `Quiz` da assegnare al tema.
 */
	public void setQuiz(Set<Quiz> quiz) {
		this.quiz = quiz;
	}
	
/**
 * Restituisce l'insieme di oggetti `Domanda` associati a questo tema.
 *
 * @return L'insieme di oggetti `Domanda`.
 */
	public Set<Domanda> getDomande() {
		return domande;
	}

/**
 * Imposta l'insieme di oggetti `Domanda` associati a questo tema.
 *
 * @param domande L'insieme di oggetti `Domanda` da assegnare al tema.
 */
	public void setDomande(Set<Domanda> domande) {
		this.domande = domande;
	}

/**
 * Restituisce una rappresentazione testuale dell'oggetto `TemaQuiz`.
 *
 * @return Una stringa che descrive l'oggetto `TemaQuiz`.
 */
	@Override
	public String toString() {
		return "TemaQuiz [id=" + id + ", nome=" + nome + ", quizList=" + quiz + "]";
	}
	
}
