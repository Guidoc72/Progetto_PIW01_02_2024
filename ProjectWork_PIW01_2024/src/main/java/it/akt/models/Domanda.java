package it.akt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "domanda")
public class Domanda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quesito", length = 1000, nullable = false)
	private String quesito;

	@Column(name = "risposta1", length = 1000, nullable = false)
	private String risposta1;

	@Column(name = "risposta2", length = 1000, nullable = false)
	private String risposta2;

	@Column(name = "risposta3", length = 1000, nullable = false)
	private String risposta3;

	@Column(name = "risposta4", length = 1000, nullable = false)
	private String risposta4;

	@Column(name = "risposta_giusta", nullable = false)
	private int risposta_giusta;

	@ManyToMany(mappedBy = "domande")
	private Set<Quiz> quiz = new HashSet<>();
	

	@ManyToOne
	@JoinColumn(name = "tema_id", nullable = false)
	private TemaQuiz tema;
	
	public Domanda() {
	}

	public Domanda(Long id, String quesito, String risposta1, String risposta2, String risposta3, String risposta4,
			int risposta_giusta, Set<Quiz> quiz, TemaQuiz tema) {
		this.id = id;
		this.quesito = quesito;
		this.risposta1 = risposta1;
		this.risposta2 = risposta2;
		this.risposta3 = risposta3;
		this.risposta4 = risposta4;
		this.risposta_giusta = risposta_giusta;
		this.quiz = quiz;
		this.tema = tema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuesito() {
		return quesito;
	}

	public void setQuesito(String quesito) {
		this.quesito = quesito;
	}

	public String getRisposta1() {
		return risposta1;
	}

	public void setRisposta1(String risposta1) {
		this.risposta1 = risposta1;
	}

	public String getRisposta2() {
		return risposta2;
	}

	public void setRisposta2(String risposta2) {
		this.risposta2 = risposta2;
	}

	public String getRisposta3() {
		return risposta3;
	}

	public void setRisposta3(String risposta3) {
		this.risposta3 = risposta3;
	}

	public String getRisposta4() {
		return risposta4;
	}

	public void setRisposta4(String risposta4) {
		this.risposta4 = risposta4;
	}

	public int getRisposta_giusta() {
		return risposta_giusta;
	}

	public void setRisposta_giusta(int risposta_giusta) {
		this.risposta_giusta = risposta_giusta;
	}

	public Set<Quiz> getQuiz() {
		return quiz;
	}
	
	public void setQuiz(Set<Quiz> quiz) {
		this.quiz = quiz;
	}
	
	public TemaQuiz getTema() {
		return tema;
	}
	
	public void setTema(TemaQuiz tema) {
		this.tema = tema;
	}

	@Override
	public String toString() {
		return "Domanda [id=" + id + ", quesito=" + quesito + ", risposta1=" + risposta1 + ", risposta2=" + risposta2
				+ ", risposta3=" + risposta3 + ", risposta4=" + risposta4 + ", risposta_giusta=" + risposta_giusta
				+ ", quiz=" + quiz + ", tema=" + tema + "]";
	}
	
	


}
