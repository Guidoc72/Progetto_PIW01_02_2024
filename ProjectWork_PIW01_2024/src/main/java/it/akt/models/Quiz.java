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

@Entity
@Table(name = "quiz")
public class Quiz {

	//indica l'id del Quiz
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //indica la data del Quiz
    @Column(name = "data", nullable = false)
    @NotEmpty
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
    
    //costruttore
    public Quiz(Long id, @NotEmpty LocalDate data, TemaQuiz temaQuiz, Set<Aula> aule, Set<Risultato> risultati,
    		Set<Domanda> domande) {
    	super();
    	this.id = id;
    	this.data = data;
    	this.temaQuiz = temaQuiz;
    	this.aule = aule;
    	this.risultati = risultati;
    	this.domande = domande;
    }
    
    public Quiz() {}

	//getter and setter

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public TemaQuiz getTemaQuiz() {
		return temaQuiz;
	}

	public void setTemaQuiz(TemaQuiz temaQuiz) {
		this.temaQuiz = temaQuiz;
	}

	public Set<Aula> getAule() {
		return aule;
	}

	public void setAule(Set<Aula> aule) {
		this.aule = aule;
	}

	public Set<Risultato> getRisultati() {
		return risultati;
	}


	public void setRisultati(Set<Risultato> risultati) {
		this.risultati = risultati;
	}


	public Set<Domanda> getDomande() {
		return domande;
	}


	public void setDomande(Set<Domanda> domande) {
		this.domande = domande;
	}

	//ToString
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", data=" + data + ", temaQuiz=" + temaQuiz + ", aule=" + aule + ", risultati="
				+ risultati + ", domande=" + domande + "]";
	}
	

	
}