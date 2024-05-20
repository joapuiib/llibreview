insert into llibre (isbn, titol, resum, nombre_pagines, data_publicacio, ruta_imatge) values
    ('isbn1', 'Llibre 1', 'Resum 1', 100, '2024-01-01', 'imatge1.png'),
    ('isbn2', 'Llibre 2', 'Resum 2', 200, '2024-01-02', 'imatge2.png'),
    ('isbn3', 'Llibre 3', 'Resum 3', 300, '2024-01-03', 'imatge3.png'),
    ('isbn4', 'Llibre 4', 'Resum 4', 400, '2024-01-04', 'imatge4.png'),
    ('isbn5', 'Llibre 5', 'Resum 5', 500, '2024-01-05', 'imatge5.png'),
    ('isbn6', 'Llibre 6', 'Resum 6', 600, '2024-01-06', 'imatge6.png');

insert into autor(id_autor, nom, biografia, data_naixement, ruta_imatge) values
    (1, 'Autor 1', 'Biografia 1', '2000-01-01', 'imatge1.png'),
    (2, 'Autor 2', 'Biografia 2', '2000-01-02', 'imatge2.png'),
    (3, 'Autor 3', 'Biografia 3', '2000-01-03', 'imatge3.png');

insert into escriu(isbn, id_autor) values
    ('isbn2', 1),
    ('isbn3', 1),
    ('isbn3', 2);
