package it.akt.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tema")
public class TemaQuiz {

	//indica l'id del Tema
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //indica il nome del tema
    @Column(name = "nome")
    private String nome;

    // Relazione uno a molti con Quiz
    @OneToMany
    private List<Quiz> quizList;

    //costruttore
	public TemaQuiz(Long id, String nome, List<Quiz> quizList) {
		this.id = id;
		this.nome = nome;
		this.quizList = quizList;
	}
    
	public TemaQuiz() {}

	//getter and setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeTema() {
		return nome;
	}

	public void setNomeTema(String nome) {
		this.nome = nome;
	}

	public List<Quiz> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<Quiz> quizList) {
		this.quizList = quizList;
	}
	
	//ToString
	@Override
	public String toString() {
		return "TemaQuiz [id=" + id + ", nome=" + nome + ", quizList=" + quizList + "]";
	}
	
}
