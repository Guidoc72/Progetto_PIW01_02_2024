CREATE DATABASE IF NOT EXISTS QuizDB;
USE QuizDB;

CREATE TABLE IF NOT EXISTS aula (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(10) NOT NULL UNIQUE
);

create table if not exists risultato (
   id int primary key not null auto_increment,
   id_quiz int not null,
   id_utente int not null,
   punteggio int,
   risposta1 int,
   risposta2 int,
   risposta3 int,
   risposta4 int,
   risposta5 int,
   risposta6 int,
   risposta7 int,
   rsiposta8 int,
   risposta9 int,
   risposta10 int,
   foreign key (id_quiz) references quiz(id),
   foreign key (id_utente) references utente(id)
);

-- Creazione della tabella Tema
CREATE TABLE tema (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL UNIQUE
);

-- Creazione della tabella Quiz con chiave esterna verso Tema
CREATE TABLE quiz (
  id INT PRIMARY KEY AUTO_INCREMENT,
  data DATE,
  tema_id INT,
  FOREIGN KEY (tema_id) REFERENCES tema(id),
  FOREIGN KEY (aula_id) REFERENCES aula(id)
);

create table if not exists domanda (
	id int primary key auto_increment,
    quesito varchar(1000) not null,
    risposta1 varchar(1000) not null,
    risposta2 varchar(1000) not null,
    risposta3 varchar(1000) not null,
    risposta4 varchar(1000) not null,
    risposta_giusta int not null,
    id_tema int not null,
    foreign key(id_tema) references tema(id)
);

CREATE TABLE IF NOT EXISTS utente (
    id int primary key not null auto_increment,
    nome varchar(45) not null,
    cognome	varchar(45) not null,
    email varchar(100) not null,
    telefono varchar(20) not null,
    ruolo tinyint not null default 1,
    isEnabled tinyint not null default 1,
    password varchar(100) not null
);
    
create table if not exists quiz_has_domanda (
    quiz_id int not null,
    domanda_id int not null,
    primary key(quiz_id, domanda_id),
    foreign key (quiz_id) references quiz(id),
    foreign key (domanda_id) references domanda(id)
);

CREATE TABLE IF NOT EXISTS quiz_has_utente(
    quiz_id int not null,
    utente_id int not null,
    primary key (quiz_id, utente_id),
    foreign key (utente_id) references utente(id),
    foreign key (quiz_id) references quiz(id)
);
    
CREATE TABLE IF NOT EXISTS quiz_has_aula (
	quiz_id INT NOT NULL,
    aula_id INT NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES quiz(id),
    FOREIGN KEY (aula_id) REFERENCES aula(id)
);
    
CREATE TABLE IF NOT EXISTS utente_has_aula (
    utente_id INT NOT NULL,
    aula_id INT NOT NULL,
    PRIMARY KEY (utente_id, aula_id),
    FOREIGN KEY (utente_id) REFERENCES utente(id),
    FOREIGN KEY (aula_id) REFERENCES aula(id)
);
