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
	risultato_id INT NOT NULL,
    aula_id INT NOT NULL,
    FOREIGN KEY (risultato_id) REFERENCES risultato(id),
    FOREIGN KEY (aula_id) REFERENCES aula(id)
);