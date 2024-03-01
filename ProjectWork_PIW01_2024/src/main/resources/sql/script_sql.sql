create table Risultato(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    punteggio INT,
    data DATE,
    Risposta1 INT,
    Risposta2 INT,
    Risposta3 INT,
    Risposta4 INT,
    Risposta5 INT,
    Risposta6 INT,
    Risposta7 INT,
    Risposta8 INT,
    Risposta9 INT,
    Risposta10 INT,
    Quiz_id INT,
    Utente_id INT
    FOREIGN KEY(Quiz_id) REFERENCES Quiz(id),
    FOREIGN KEY(Utente_id) REFERENCES Utente(id)

);