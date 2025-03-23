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

insert into genere(id_genere, nom_ca, nom_en) values
    (1, 'Genere 1', 'Genre 1'),
    (2, 'Genere 2', 'Genre 2'),
    (3, 'Genere 3', 'Genre 3');

insert into llibre_genere(isbn, id_genere) values
    ('isbn1', 1),
    ('isbn1', 2),
    ('isbn2', 1);

insert into usuari(username, email, data_registre, password_hash) values
    ('user1', 'user1@localhost', '2021-01-01', '$2a$10$7P2f2u72PfbOGJtL7CQTruW5WZ0.cgT9jUbnbfo.2wvE.gaaYVvn2'), /* password: user1 */
    ('user2', 'user2@localhost', '2021-01-01', '$2a$10$6wkDziA7C5460lfnzdXWbuLdSHUz.m8McydWD3ToZfBgOgE7nPQE.'), /* password: user2 */
    ('user3', 'user3@localhost', '2021-01-01', '$2a$10$il0FptkiuLRtF7swrx7KoudhNtw0VIQHjWtX4.RNFzFA7l5TFIs.i'), /* password: user3 */
    ('user4', 'user4@localhost', '2021-01-01', '$2a$10$6vcrnNp68l5RnW6Mh56CV.KBo8oM1xgY/Asb0vIYV/.3TwMVbhX5G'); /* password: user4 */

insert into valoracio(isbn, username, data, puntuacio) values
    ('isbn1', 'user1', '2024-05-01', 7),
    ('isbn2', 'user1', '2024-05-01', 7),
    ('isbn2', 'user2', '2024-05-01', 8);

insert into ressenya(isbn, username, data, comentari) values
    ('isbn1', 'user1', '2024-05-02', 'Ressenya 1'),
    ('isbn2', 'user1', '2024-05-03', 'Ressenya 2');
