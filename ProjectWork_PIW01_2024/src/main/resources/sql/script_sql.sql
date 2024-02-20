create table if not exists domanda (
id int primary key auto_increment,
quesito varchar (1000) NOT NULL,
risposta1 varchar(1000) not null,
risposta2 varchar (1000) not null,
risposta3 varchar (1000) not null,
risposta4 varchar (1000) not null,
rispostaGiusta int not null,
id_tema int not null,
FOREIGN KEY (id_tema) REFERENCES temaquiz(id)
);
create table if not exists quiz_has_domanda (
quiz_id int not null,
domanda_id int not null,
PRIMARY KEY (quiz_id, domanda_id),
FOREIGN KEY (quiz_id) REFERENCES quiz(id),   
 FOREIGN KEY (domanda_id) REFERENCES domanda(id)
);

-- Creazione della tabella TemaQuiz
CREATE TABLE temaquiz (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nometema VARCHAR(255) NOT NULL
);

-- Creazione della tabella Quiz con chiave esterna verso TemaQuiz
CREATE TABLE quiz (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data DATE,
    tema_quiz_id INT,
    FOREIGN KEY (tema_quiz_id) REFERENCES temaquiz(id)
);

