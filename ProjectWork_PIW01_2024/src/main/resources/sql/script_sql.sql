CREATE DATABASE IF NOT EXISTS QuizDB;

USE QuizDB;

CREATE TABLE IF NOT EXISTS utente (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    cognome	VARCHAR(45) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    ruolo TINYINT NOT NULL,
    abilitato BIT NOT NULL,
    password VARCHAR(100) NOT NULL,
    password_token VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS aula (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(10) NOT NULL UNIQUE
);

-- Creazione della tabella Tema
CREATE TABLE IF NOT EXISTS tema (
   id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   nome VARCHAR(25) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS domanda (
	id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    quesito VARCHAR(1000) NOT NULL,
    risposta1 VARCHAR(1000) NOT NULL,
    risposta2 VARCHAR(1000) NOT NULL,
    risposta3 VARCHAR(1000) NOT NULL,
    risposta4 VARCHAR(1000) NOT NULL,
    risposta_giusta INT NOT NULL,
    tema_id BIGINT NOT NULL,
    FOREIGN KEY(tema_id) REFERENCES tema(id)
);

-- Creazione della tabella Quiz con chiave esterna verso Tema
CREATE TABLE IF NOT EXISTS quiz (
   id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   data DATE,
   tema_id BIGINT NOT NULL,
   aula_id BIGINT NOT NULL,
   FOREIGN KEY (tema_id) REFERENCES tema(id),
   FOREIGN KEY (aula_id) REFERENCES aula(id)
);

CREATE TABLE IF NOT EXISTS risultato (
   id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   punteggio INT,
   risposta1 INT,
   risposta2 INT,
   risposta3 INT,
   risposta4 INT,
   risposta5 INT,
   risposta6 INT,
   risposta7 INT,
   risposta8 INT,
   risposta9 INT,
   risposta10 INT,
   quiz_id BIGINT NOT NULL,
   utente_id BIGINT NOT NULL,
   FOREIGN KEY (quiz_id) REFERENCES quiz(id),
   FOREIGN KEY (utente_id) REFERENCES utente(id)
);
    
CREATE TABLE IF NOT EXISTS quiz_has_domanda (
    quiz_id BIGINT NOT NULL,
    domanda_id BIGINT NOT NULL,
    PRIMARY KEY(quiz_id, domanda_id),
	FOREIGN KEY (quiz_id) REFERENCES quiz(id),
	FOREIGN KEY (domanda_id) REFERENCES domanda(id)
);

CREATE TABLE IF NOT EXISTS quiz_has_utente(
    quiz_id BIGINT NOT NULL,
    utente_id BIGINT NOT NULL,
    PRIMARY KEY (quiz_id, utente_id),
	FOREIGN KEY (utente_id) REFERENCES utente(id),
    FOREIGN KEY (quiz_id) REFERENCES quiz(id)
);
    
CREATE TABLE IF NOT EXISTS quiz_has_aula (
	quiz_id BIGINT NOT NULL,
    aula_id BIGINT NOT NULL,
    PRIMARY KEY (quiz_id, aula_id),
    FOREIGN KEY (quiz_id) REFERENCES quiz(id),
    FOREIGN KEY (aula_id) REFERENCES aula(id)
);
    
CREATE TABLE IF NOT EXISTS utente_has_aula (
    utente_id BIGINT NOT NULL,
    aula_id BIGINT NOT NULL,
    PRIMARY KEY (utente_id, aula_id),
    FOREIGN KEY (utente_id) REFERENCES utente(id),
    FOREIGN KEY (aula_id) REFERENCES aula(id)
);