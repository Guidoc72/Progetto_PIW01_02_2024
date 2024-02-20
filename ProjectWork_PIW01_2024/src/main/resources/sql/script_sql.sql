CREATE DATABASE IF NOT EXISTS QuizDB;
USE QuizDB;

CREATE TABLE IF NOT EXISTS aula (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(10) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS utente_has_aula (
    utente_id INT NOT NULL,
    aula_id INT NOT NULL,
    FOREIGN KEY (utente_id) REFERENCES utente(id),
    FOREIGN KEY (aula_id) REFERENCES aula(id)
);

CREATE TABLE IF NOT EXISTS risultato_has_aula (
    quiz_id INT NOT NULL,
    utente_id INT NOT NULL,
    aula_id INT NOT NULL,
    FOREIGN KEY (quiz_id, utente_id) REFERENCES risultato(id_Quiz,id_Utente),
    FOREIGN KEY (aula_id) REFERENCES aula(id),
    PRIMARY KEY(quiz_id,utente_id,aula_id)
    );

create table Risultato (
   id_Quiz int,
   id_Utente int,
   punteggio int,
   Data date,
   Risposta1 int,
   Risposta2 int,
   Risposta3 int,
   Risposta4 int,
   Risposta5 int,
   Risposta6 int,
   Risposta7 int,
   Rsiposta8 int,
   Risposta9 int,
   Risposta10 int,
   Primary key(id_Quiz, id_Utente)
);

create table Quiz_Has_Risultato (
    Quiz_id int,
    Risultato_id_Utente int,

    Primary key (Quiz_id, Risultato_id_Utente),
    Foreign key (Quiz_id) references Quiz(id),
    Foreign key (Quiz_id,Risultato_id_Utente) references Risultato (id_Quiz,id_Utente)
);

-- Creazione della tabella TemaQuiz
CREATE TABLE tema (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL
);

-- Creazione della tabella Quiz con chiave esterna verso Tema
CREATE TABLE quiz (
  id INT PRIMARY KEY AUTO_INCREMENT,
  data_quiz DATE,
  tema_id INT,
  FOREIGN KEY (tema_id) REFERENCES tema(id)
);

create table if not exists domanda (
    id int primary key auto_increment,
    quesito varchar(1000) not null,
    risposta1 varchar(1000) not null,
    risposta2 varchar(1000) not null,
    risposta3 varchar(1000) not null,
    risposta4 varchar(1000) not null,
    rispostaGiusta int not null,
    id_tema int not null,
    foreign key(id_tema) references temaquiz(id)
    );

create table if not exists quiz_has_domanda (
    quiz_id int not null,
    domanda_id int not null,
    primary key(quiz_id, domanda_id),
    foreign key (quiz_id) references quiz(id),
    foreign key (domanda_id) references domanda(id)
    );

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
