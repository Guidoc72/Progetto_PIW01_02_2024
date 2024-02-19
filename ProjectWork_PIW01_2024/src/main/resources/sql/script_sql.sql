
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

