insert into llibre (isbn, titol, resum, nombre_pagines, data_publicacio) values
    ('1', 'Canto jo i la muntanya balla', 'Resum llibre 1', 162, '2019-05-8');

insert into autor(id_autor, nom, data_naixement) values
    (0, 'Irene Solà', '1990-08-17');

insert into escriu(isbn, id_autor) values
    ('1', 0);