package it.akt.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

	    @Column(name = "id_tema", nullable = false)
	    private int id_tema;
	    
	    public Domanda () {}
		
		public Domanda(String quesito, String risposta1, String risposta2, String risposta3, String risposta4,
				int risposta_giusta, int id_tema) {
			this.setQuesito(quesito);
			this.setRisposta1(risposta1);
			this.setRisposta2(risposta2);
			this.setRisposta3(risposta3);
			this.setRisposta4(risposta4);
			this.setRisposta_giusta(risposta_giusta);
			this.setId_tema(id_tema);
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

		public int getId_tema() {
			return id_tema;
		}

		public void setId_tema(int id_tema) {
			this.id_tema = id_tema;
		}

	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="quiz_has_domanda",
			joinColumns= @JoinColumn(name="domanda_id"),
			inverseJoinColumns=@JoinColumn(name="quiz_id"))
	private Set<Quiz> quizzes = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="id_tema", nullable = false)
	private Tema tema;
	
	
	
}
