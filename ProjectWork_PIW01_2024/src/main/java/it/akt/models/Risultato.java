package it.akt.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="risultato")
public class Risultato {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @Column(name = "punteggio", nullable = false)
    private int punteggio;
    
    @NotEmpty
    @Column(name = "risposta1", nullable = false)
    private int risposta1;
    
    @NotEmpty
    @Column(name = "risposta2", nullable = false)
    private int risposta2;
    
    @NotEmpty
    @Column(name = "risposta3", nullable = false)
    private int risposta3;
    
    @NotEmpty
    @Column(name = "risposta4", nullable = false)
    private int risposta4;
    
    @NotEmpty
    @Column(name = "risposta5", nullable = false)
    private int risposta5;
    
    @NotEmpty
    @Column(name = "risposta6", nullable = false)
    private int risposta6;
    
    @NotEmpty
    @Column(name = "risposta7", nullable = false)
    private int risposta7;
    
    @NotEmpty
    @Column(name = "risposta8", nullable = false)
    private int risposta8;
    
    @NotEmpty
    @Column(name = "risposta9", nullable = false)
    private int risposta9;
    
    @NotEmpty
    @Column(name = "risposta10", nullable = false)
    private int risposta10;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz; 		

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

	public Risultato(Long id, int punteggio, int risposta1,
			int risposta2, int risposta3, int risposta4, int risposta5,
			int risposta6, int risposta7, int risposta8, int risposta9,
			int risposta10, Quiz quiz, Utente utente) {
		this.id = id;
		this.punteggio = punteggio;
		this.risposta1 = risposta1;
		this.risposta2 = risposta2;
		this.risposta3 = risposta3;
		this.risposta4 = risposta4;
		this.risposta5 = risposta5;
		this.risposta6 = risposta6;
		this.risposta7 = risposta7;
		this.risposta8 = risposta8;
		this.risposta9 = risposta9;
		this.risposta10 = risposta10;
		this.quiz = quiz;
		this.utente = utente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	public int getRisposta1() {
		return risposta1;
	}

	public void setRisposta1(int risposta1) {
		this.risposta1 = risposta1;
	}

	public int getRisposta2() {
		return risposta2;
	}

	public void setRisposta2(int risposta2) {
		this.risposta2 = risposta2;
	}

	public int getRisposta3() {
		return risposta3;
	}

	public void setRisposta3(int risposta3) {
		this.risposta3 = risposta3;
	}

	public int getRisposta4() {
		return risposta4;
	}

	public void setRisposta4(int risposta4) {
		this.risposta4 = risposta4;
	}

	public int getRisposta5() {
		return risposta5;
	}

	public void setRisposta5(int risposta5) {
		this.risposta5 = risposta5;
	}

	public int getRisposta6() {
		return risposta6;
	}

	public void setRisposta6(int risposta6) {
		this.risposta6 = risposta6;
	}

	public int getRisposta7() {
		return risposta7;
	}

	public void setRisposta7(int risposta7) {
		this.risposta7 = risposta7;
	}

	public int getRisposta8() {
		return risposta8;
	}

	public void setRisposta8(int risposta8) {
		this.risposta8 = risposta8;
	}

	public int getRisposta9() {
		return risposta9;
	}

	public void setRisposta9(int risposta9) {
		this.risposta9 = risposta9;
	}

	public int getRisposta10() {
		return risposta10;
	}

	public void setRisposta10(int risposta10) {
		this.risposta10 = risposta10;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "Risultato [id=" + id + ", punteggio=" + punteggio + " risposta1=" + risposta1
				+ ", risposta2=" + risposta2 + ", risposta3=" + risposta3 + ", risposta4=" + risposta4 + ", risposta5="
				+ risposta5 + ", risposta6=" + risposta6 + ", risposta7=" + risposta7 + ", risposta8=" + risposta8
				+ ", risposta9=" + risposta9 + ", risposta10=" + risposta10 + ", quiz=" + quiz + ", utente=" + utente
				+ "]";
	}
	
        
}
