package it.akt.models;

import java.sql.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="risultato")
public class Risultato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "punteggio")
    int punteggio;
    @Column(name = "data")
    Date data;
    @Column(name = "Risposta1")
    int Risposta1;
    @Column(name = "Risposta2")
    int Risposta2;
    @Column(name = "Risposta3")
    int Risposta3;
    @Column(name = "Risposta4")
    int Risposta4;
    @Column(name = "Risposta5")
    int Risposta5;
    @Column(name = "Risposta6")
    int Risposta6;
    @Column(name = "Risposta7")
    int Risposta7;
    @Column(name = "Risposta8")
    int Risposta8;
    @Column(name = "Risposta9")
    int Risposta9;
    @Column(name = "Risposta10")
    int Risposta10;

    @ManyToOne
    @JoinTable(name = "quiz",
        joinColumns = @JoinColumn(name = "id")
    )
    Quiz quiz;

    @ManyToOne
    @JoinTable(name = "utente",
            joinColumns = @JoinColumn(name = "id")
    )
   Utente utente;

}
