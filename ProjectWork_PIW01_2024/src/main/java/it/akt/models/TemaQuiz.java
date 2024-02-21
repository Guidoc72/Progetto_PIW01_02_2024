package it.akt.models;

import java.util.List;

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
public class TemaQuiz {

	//indica l'id del Tema
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //indica il nome del tema
    @Column(name = "nome", nullable = false)
    @NotEmpty
    private String nome;

    // Relazione uno a molti con Quiz
    @OneToMany(mappedBy = "tema")
    private List<Quiz> quiz;

    //Relazione uno a molti con Domanda
    @OneToMany(mappedBy = "tema")
    private List<Domanda> domande;
    
    //costruttore
    public TemaQuiz(Long id, String nome, List<Quiz> quiz, List<Domanda> domande) {
    	this.id = id;
    	this.nome = nome;
    	this.quiz = quiz;
    	this.domande = domande;
    }
	
	public TemaQuiz() {}

	//getter and setter
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

	public List<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(List<Quiz> quiz) {
		this.quiz = quiz;
	}
	
	public List<Domanda> getDomande() {
		return domande;
	}

	public void setDomande(List<Domanda> domande) {
		this.domande = domande;
	}

	//ToString
	@Override
	public String toString() {
		return "TemaQuiz [id=" + id + ", nome=" + nome + ", quizList=" + quiz + "]";
	}
	
}
