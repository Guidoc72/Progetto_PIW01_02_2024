package it.akt.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

	    @Column(name = "rispostaGiusta", nullable = false)
	    private int rispostaGiusta;

	    @Column(name = "id_tema", nullable = false)
	    private int idTema;

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

		public int getRispostaGiusta() {
			return rispostaGiusta;
		}

		public void setRispostaGiusta(int rispostaGiusta) {
			this.rispostaGiusta = rispostaGiusta;
		}

		public int getIdTema() {
			return idTema;
		}

		public void setIdTema(int idTema) {
			this.idTema = idTema;
		}

		@ManyToMany(cascade=CascadeType.PERSIST)
		@JoinTable(name="quiz_has_domanda",
			joinColumns= @JoinColumn(name="domanda_id"),
			inverseJoinColumns=@JoinColumn(name="quiz_id"))
	private Set<Quiz> quizzes=new HashSet<>();
	
	
	
}
