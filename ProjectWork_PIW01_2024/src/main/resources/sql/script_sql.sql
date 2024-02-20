-- Creazione della tabella TemaQuiz
CREATE TABLE tema (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL
);

-- Creazione della tabella Quiz con chiave esterna verso TemaQuiz
CREATE TABLE quiz (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data_quiz DATE,
    tema_quiz_id INT,
    FOREIGN KEY (tema_quiz_id) REFERENCES tema(id)
);