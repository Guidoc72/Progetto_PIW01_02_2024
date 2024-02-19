package it.akt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "quiz")
public class Quiz {

	//indica l'id del Quiz
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //indica la data del Quiz
    @Column(name = "data")
    private LocalDate data;

    //Relazione molti a uno con TemaQuiz
    @ManyToOne
    @JoinColumn(name = "id_tema")
    private TemaQuiz temaQuiz;
    
    //costruttore
    public Quiz(Long id, LocalDate data, TemaQuiz temaQuiz) {
		this.id = id;
		this.data = data;
		this.temaQuiz = temaQuiz;
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

	//ToString
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", data=" + data + ", temaQuiz=" + temaQuiz + "]";
		
	}

	
}