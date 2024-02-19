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