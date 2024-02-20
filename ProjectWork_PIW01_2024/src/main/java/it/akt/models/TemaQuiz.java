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
@Table(name = "tema_quiz")
public class TemaQuiz {

	//indica l'id del Tema
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //indica il nome del tema
    @Column(name = "nometema")
    private String nometema;

    // Relazione uno a molti con Quiz
    @OneToMany
    private List<Quiz> quizList;

    //costruttore
	public TemaQuiz(Long id, String nometema, List<Quiz> quizList) {
		this.id = id;
		this.nometema = nometema;
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
		return nometema;
	}

	public void setNomeTema(String nometema) {
		this.nometema = nometema;
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
		return "TemaQuiz [id=" + id + ", nome=" + nometema + ", quizList=" + quizList + "]";
	}
	
}
