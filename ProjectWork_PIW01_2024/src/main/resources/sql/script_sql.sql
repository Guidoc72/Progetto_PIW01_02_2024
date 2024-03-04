<<<<<<< HEAD
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
=======

CREATE TABLE IF NOT EXISTS utente(
    id 			int not null auto_increment,
    nome 		varchar(45) not null,
    cognome		varchar(45) not null,
    email 		varchar(100) not null,
    telefono 	varchar(20) not null,
    role 		int not null,
    isEnabled 	tinyint NOT NULL default 1,
    password 	varchar(100) not null,
    primary key (id)
    );

CREATE TABLE IF NOT EXISTS quiz_has_utente(
    quiz_id int not null,
    utente_id int not null,
    primary key (quiz_id, utente_id),
    foreign key (utente_id) references utente(id)
    on delete no action
    on update cascade,
    foreign key (quiz_id) references quiz(id)
    on delete no action
    on update cascade
    );
>>>>>>> refs/remotes/origin/Emanuele-Rodrigo
