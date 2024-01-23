insert into llibre (isbn, titol, resum, nombre_pagines, data_publicacio) values
    ('1', 'Canto jo i la muntanya balla', 'Resum 1', 162, '2019-05-08'),
    ('2', 'Un amor', 'Resum 2', 192, '2020-09-02'),
    ('3', 'La familia', 'Resum 3', 232, '2022-09-14'),
    ('4', 'Jo, Robot', 'Resum 4', 328, '1950-12-02'),
    ('5', 'Bóvedas de acero', 'Resum 5', 272, '1954-06-01'),
    ('6', 'El sol desnudo', 'Resum 6', 288, '1957-01-01');

insert into autor(id_autor, nom, data_naixement) values
    (0, 'Irene Solà', '1990-08-17'),
    (1, 'Sara Mesa', '1996-01-01'),
    (2, 'Isaac Asimov', '1919-10-04');

insert into escriu(isbn, id_autor) values
    ('1', 0),
    ('2', 1),
    ('3', 1),
    ('4', 2),
    ('5', 2),
    ('6', 2);
