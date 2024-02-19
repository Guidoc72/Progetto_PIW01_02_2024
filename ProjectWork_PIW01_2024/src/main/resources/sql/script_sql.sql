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
                                    Foreign key (Quiz_id) references Quiz(Id),
                                    Foreign key (Risultato_id_Utente) references Risultato (id_Utente)
);