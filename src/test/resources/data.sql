insert into llibre (isbn, titol, resum, nombre_pagines, data_publicacio, ruta_imatge) values
    ('1', 'Llibre 1', 'Resum 1', 100, '2024-01-01', 'imatge1.png'),
    ('2', 'Llibre 2', 'Resum 2', 200, '2024-01-02', 'imatge2.png'),
    ('3', 'Llibre 3', 'Resum 3', 300, '2024-01-03', 'imatge3.png'),
    ('4', 'Llibre 4', 'Resum 4', 400, '2024-01-04', 'imatge4.png'),
    ('5', 'Llibre 5', 'Resum 5', 500, '2024-01-05', 'imatge5.png'),
    ('6', 'Llibre 6', 'Resum 6', 600, '2024-01-06', 'imatge6.png');

insert into autor(id_autor, nom, data_naixement) values
    (0, 'Autor 0', '1990-01-01'),
    (1, 'Autor 1', '1991-01-01'),
    (2, 'Autor 2', '1992-01-01');

insert into escriu(isbn, id_autor) values
    ('1', 0),
    ('2', 1),
    ('3', 1),
    ('4', 2),
    ('5', 2),
    ('6', 2);
