package it.akt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Rappresenta un quiz.
 *
 * @param id       L'ID del quiz.
 * @param data     La data del quiz.
 * @param temaQuiz Il tema del quiz.
 * @param aule     Le aule associate al quiz.
 * @param risultati I risultati ottenuti nel quiz.
 * @param domande  Le domande del quiz.
 * @param utenti   Gli utenti coinvolti nel quiz.
 * @author Samuele Romanelli
 */
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    //Relazione molti a uno con TemaQuiz
    @ManyToOne
    @JoinColumn(name = "id_tema")
    private TemaQuiz temaQuiz;
    
    //Relazione Molti a Molti tra Quiz e Aula
    @ManyToMany
    @JoinTable(
        name = "quiz_has_aula",
        joinColumns = @JoinColumn(name = "quiz_id"),
        inverseJoinColumns = @JoinColumn(name = "aula_id")
    )
    private Set<Aula> aule = new HashSet<>();
    
    //Relazione Uno a Molti tra Quiz e Risultato
    @OneToMany(mappedBy = "quiz")
    private Set<Risultato> risultati = new HashSet<>();
    
    //Relazione Molti a Molti tra Quiz e Domanda
    @ManyToMany
    @JoinTable(
        name = "quiz_has_domanda",
        joinColumns = @JoinColumn(name = "quiz_id"),
        inverseJoinColumns = @JoinColumn(name = "domanda_id")
    )
    private Set<Domanda> domande = new HashSet<>();  
    
    //Relazione Molti a Molti tra Quiz e Utente
    @ManyToMany
    @JoinTable(
        name = "quiz_has_utente",
        joinColumns = @JoinColumn(name = "quiz_id"),
        inverseJoinColumns = @JoinColumn(name = "utente_id")
    )
    private Set<Utente> utenti = new HashSet<>();
    
    /**
     * Costruisce un oggetto Quiz con i parametri specificati.
     *
     * @param id        L'ID del quiz.
     * @param data      La data del quiz.
     * @param temaQuiz  Il tema del quiz.
     * @param aule      Le aule associate al quiz.
     * @param risultati I risultati ottenuti nel quiz.
     * @param domande   Le domande del quiz.
     */
    public Quiz(Long id, LocalDate data, TemaQuiz temaQuiz, Set<Aula> aule, Set<Risultato> risultati,
    		Set<Domanda> domande) {
    	this.id = id;
    	this.data = data;
    	this.temaQuiz = temaQuiz;
    	this.aule = aule;
    	this.risultati = risultati;
    	this.domande = domande;
    }
    
    /**
     * Costruisce un oggetto Quiz senza parametri.
     */
    public Quiz() {}

	//GETTER AND SETTER
    
    /**
     * Imposta l'ID del quiz.
     *
     * @param id L'ID del quiz da impostare.
     */
    public void setId(Long id) {
    	this.id = id;
    }

    /**
     * Restituisce l'ID del quiz.
     *
     * @return L'ID del quiz.
     */
	public Long getId() {
		return id;
	}

	/**
	 * Imposta la data del quiz.
	 *
	 * @param data La data del quiz da impostare.
	 */
	public void setData(LocalDate data) {
		this.data = data;
	}

	/**
	 * Restituisce la data del quiz.
	 *
	 * @return La data del quiz.
	 */
	public LocalDate getData() {
		return data;
	}

	/**
	 * Imposta il tema del quiz.
	 *
	 * @param temaQuiz Il tema del quiz da impostare.
	 */
	public void setTemaQuiz(TemaQuiz temaQuiz) {
		this.temaQuiz = temaQuiz;
	}

	/**
	 * Restituisce il tema del quiz.
	 *
	 * @return Il tema del quiz.
	 */
	public TemaQuiz getTemaQuiz() {
		return temaQuiz;
	}

	/**
	 * Imposta l'insieme di aule associate al quiz.
	 *
	 * @param aule L'insieme di aule da impostare.
	 */
	public void setAule(Set<Aula> aule) {
		this.aule = aule;
	}

	/**
	 * Restituisce l'insieme di aule associate al quiz.
	 *
	 * @return L'insieme di aule associate al quiz.
	 */
	public Set<Aula> getAule() {
		return aule;
	}

	/**
	 * Imposta l'insieme di risultati ottenuti nel quiz.
	 *
	 * @param risultati L'insieme di risultati da impostare.
	 */
	public void setRisultati(Set<Risultato> risultati) {
		this.risultati = risultati;
	}

	/**
	 * Restituisce l'insieme di risultati ottenuti nel quiz.
	 *
	 * @return L'insieme di risultati ottenuti nel quiz.
	 */
	public Set<Risultato> getRisultati() {
		return risultati;
	}

	/**
	 * Imposta l'insieme di domande del quiz.
	 *
	 * @param domande L'insieme di domande da impostare.
	 */
	public void setDomande(Set<Domanda> domande) {
		this.domande = domande;
	}

	/**
	 * Restituisce l'insieme di domande del quiz.
	 *
	 * @return L'insieme di domande del quiz.
	 */
	public Set<Domanda> getDomande() {
		return domande;
	}
	
	/**
	 * Imposta l'insieme di utenti.
	 *
	 * @param utenti L'insieme di utenti da impostare.
	 */
	public void setUtenti(Set<Utente> utenti) {
	    this.utenti = utenti;
	}

	/**
	 * Restituisce l'insieme di utenti.
	 *
	 * @return L'insieme di utenti.
	 */
	public Set<Utente> getUtente() {
	    return utenti;
	}


	/**
	 * Restituisce una rappresentazione testuale dell'oggetto Quiz.
	 *
	 * @return Una stringa che contiene le informazioni sull'ID, la data, il tema, le aule, i risultati e le domande del quiz.
	 */
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", data=" + data + ", temaQuiz=" + temaQuiz.getNome() + ", aule=" +"]";
	}
	

	
}