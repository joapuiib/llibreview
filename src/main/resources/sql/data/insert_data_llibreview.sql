insert into llibre (isbn, titol, resum, nombre_pagines, data_publicacio, ruta_imatge) values
    ('9788433915689', 'Canto jo i la muntanya balla', 'Canto jo i la muntanya balla és una novel·la d''Irene Solà publicada el 2019, de les més venudes a la literatura catalana contemporània, que ha arribat també al mercat internacional i ha estat traduïda a una vintena d''idiomes. El 2021 va ser la novel·la més prestada a la xarxa de biblioteques de Barcelona.', 162, '2019-05-8', 'canto_jo.jpeg'),
    ('9788433999030', 'Un amor', 'Nat, una joven e inexperta traductora, acaba de mudarse al pequeño núcleo rural de La Escapa. Su casero, que le regala un perro como gesto de bienvenida, no tardará en mostrar su verdadera cara y los conflictos en torno a la pobre casa alquilada se convertirán en una verdadera obsesión para ella. El resto de habitantes de la zona –la chica de la tienda, Píter el hippie, la vieja y demente Roberta, Andreas el alemán...– acogerán a Nat con aparente normalidad, mientras de fondo laten la incomprensión y la extrañeza mutuas. La Escapa terminará adquiriendo una personalidad propia, oprimente y confusa, que enfrentará a Nat no solo con sus vecinos, sino también consigo misma y sus propios fracasos.', 192, '2020-09-02', 'un_amor.webp'),
    ('9788433999542', 'La familia', '«¡En esta familia no hay secretos!», proclama al inicio de este libro Damián, el padre, un hombre de ideas e ideales fijos obsesionado con la rectitud y la pedagogía. Pero esa casa sin secretos está en realidad llena de grietas, y la opresión que se respira entre sus paredes terminará creando vías de escape, códigos clandestinos, ocultaciones, fingimientos y mentiras. Formada por dos niñas, dos niños, una madre y un padre, esta familia en apariencia normal, de clase trabajadora y llena de buenas intenciones, es la protagonista de una novela coral que abarca varias décadas y en cuyas historias laten el deseo de libertad y la crítica a los pilares que tradicionalmente han sostenido, y todavía sostienen en gran medida, la institución familiar: autoritarismo y obediencia, vergüenza y silencio. Sara Mesa vuelve a demostrar que posee un ojo clínico para desnudar comportamientos humanos, detectar heridas latentes y retratar en toda su complejidad la fragilidad, las contradicciones y las flaquezas que nos conforman. Este libro es una nueva vuelta de tuerca a la construcción de uno de los universos literarios más potentes de las letras españolas actuales y la confirmación de un talento que no deja de crecer.', 232, '2022-09-14', 'la_familia.jpeg'),
    ('9788499309255', 'Jo, Robot', 'La doctora Calvin és especialista en robopsicologia i una autoritat en la pràctica del cervell positrònic, que ha revolucionat el món dels robots. Tot i els continus progressos en aquest camp, els temors de la població humana no semblen justificats, ja que tots els robots duen impreses les Tres Lleis de la Robòtica, la primera de les quals és:"Un robot no podrà causar mai dany a un ésser humà, o, per inacció seva, deixar que li passi res de dolent."Sembla descartada, per tant, l''existència de robots rebels...', 328, '1950-12-02', 'jo_robot.jpg'),
    ('9788497937306', 'Bóvedas de acero', 'Esta excelente novela de ciencia ficción de Isaac Asimov, maestro del género, es el segundo libro de la «Serie de los robots», primer bloque de su famosa «Saga de la Fundación». En el Enclave Espacial, a las afueras de la Ciudad de Nueva York, un científico de los Mundos Exteriores ha aparecido asesinado. El detective Elijah Baley tiene que ocuparse de este caso en la para él inquietante y odiosa compañía de un robot humanoide: R. Daneel Olivaw. La investigación es delicada ya que puede terminar con el equilibrio entre los descendientes de la colonización estelar, en perfecta comunión con sus robots, y los habitantes de la Tierra, que, refugiados en grandes metrópolis subterráneas a las que llaman Ciudades, sobreviven precariamente a la falta de recursos naturales y temen a los robots.', 272, '1954-06-01', 'bovedas.jpg'),
    ('9788497937856', 'El sol desnudo', 'Mientras la sociedad terrestre rechaza a los robots humanoides, los  Mundos Exteriores, antiguas colonias de la Tierra, han basado su  economía en el trabajo de los robots, desarrollando así una sociedad  altamente tecnológica, mucho más que la terrestre, en la que los  individuos no soportan la presencia de sus congeneres: todos los  contactos sociales se producen por medio de proyecciones holográficas.  Por eso, el detective Baley no sabe por dónde empezar cuando le envían a  Solaria a resolver el primer asesinato que se produce en doscientos  años, pues todo parece apuntar, paradójicamente, a que ha sido cometido por un robot.', 288, '1957-01-01', 'el_sol_desnudo.jpg');

insert into autor(id_autor, nom, biografia, data_naixement, ruta_imatge) values
    (0, 'Irene Solà', 'Irene Solà (Malla, 1990) és escriptora i traductora. Ha publicat les novel·les Canto jo i la muntanya balla (2019) i Míriam (2021), a més de diversos llibres de poesia i contes. La seva obra ha estat traduïda a més de vint idiomes i ha rebut diversos premis, com el Documenta, el Llibres Anagrama de Novel·la i el Premi de la Crítica de narrativa catalana. Ha traduït al català obres de Virginia Woolf, Ali Smith, W. G. Sebald, Joan Didion i Anne Carson, entre d''altres. És doctora en Humanitats per la Universitat Pompeu Fabra i ha viscut a Londres, París, Berlín i Brussel·les. Actualment resideix a Barcelona.', '1990-08-17', 'irene_sola.jpg'),
    (1, 'Sara Mesa', 'Sara Mesa (Madrid, 1976) és escriptora i traductora. Ha publicat les novel·les Un incendi arran de terra (2008), El trencanous (2011), Cicatriz (2015) i Mala letra (2016), a més de diversos llibres de relats i poesia. La seva obra ha estat traduïda a més de vint idiomes i ha rebut diversos premis, com el Ojo Crítico de Narrativa, el Premio Málaga de Ensayo i el Premio Nacional de Narrativa. Ha traduït al castellà obres de Virginia Woolf, Ali Smith, W. G. Sebald, Joan Didion i Anne Carson, entre d''altres. És llicenciada en Filologia Hispànica per la Universitat Complutense de Madrid i ha viscut a Londres, París, Berlín i Brussel·les. Actualment resideix a Barcelona.', '1976-06-17', 'sara_mesa.jpg'),
    (2, 'Isaac Asimov', 'Isaac Asimov (Petróvichi, 1920 - Nova York, 1992) va néixer a Rússia i va emigrar als Estats Units amb la seva família quan era un nen. Va estudiar a la Universitat de Columbia i es va doctorar en química. Va ser professor de bioquímica a la Universitat de Boston i va publicar més de cinc-cents llibres, entre els quals destaquen les seves obres de divulgació científica i les seves novel·les de ciència-ficció. Va ser un dels autors més prolífics de la història de la literatura i va rebre nombrosos premis, com el Hugo, el Nebula i el Bram Stoker. La seva obra més coneguda és la sèrie de la Fundació, que va ser escollida com la millor sèrie de ciència-ficció de tots els temps per la World Science Fiction Society.', '1920-01-02', 'isaac_asimov.jpg');

insert into escriu(isbn, id_autor) values
    ('9788433915689', 0),
    ('9788433999030', 1),
    ('9788433999542', 1),
    ('9788499309255', 2),
    ('9788497937306', 2),
    ('9788497937856', 2);

insert into genere(id_genere, nom_ca, nom_en) values
    (1, 'Ficció literària', 'Literary fiction'),
    (2, 'Narrativa', 'Narrative'),
    (3, 'Ciència-ficció', 'Science fiction');

insert into llibre_genere(isbn, id_genere) values
    ('9788433915689', 1),
    ('9788433915689', 2),
    ('9788433999030', 2),
    ('9788433999542', 2),
    ('9788499309255', 3),
    ('9788497937306', 3),
    ('9788497937856', 3);

insert into usuari(username, email, data_registre, password) values
    ('admin', 'admin@localhost', '2022-03-01', '$2a$10$L1puu5aXVN8bP4Zk1t70fedZajz/mL3doeetBfAYuvna46I2DRL9K'),
    ('user1', 'user1@localhost', '2024-05-20', '$2a$10$7P2f2u72PfbOGJtL7CQTruW5WZ0.cgT9jUbnbfo.2wvE.gaaYVvn2'),
    ('user2', 'user2@localhost', '2024-05-20', '$2a$10$6wkDziA7C5460lfnzdXWbuLdSHUz.m8McydWD3ToZfBgOgE7nPQE.'),
    ('user3', 'user3@localhost', '2024-05-20', '$2a$10$il0FptkiuLRtF7swrx7KoudhNtw0VIQHjWtX4.RNFzFA7l5TFIs.i'),
    ('user4', 'user4@localhost', '2024-05-20', '$2a$10$6vcrnNp68l5RnW6Mh56CV.KBo8oM1xgY/Asb0vIYV/.3TwMVbhX5G');


insert into valoracio(isbn, username, data, puntuacio) values
    ('9788433915689', 'user1', '2024-05-01', 10),
    ('9788433915689', 'user2', '2024-05-01', 9),
    ('9788433915689', 'user3', '2024-05-01', 9),
    ('9788433915689', 'user4', '2024-05-01', 9),
    ('9788433999030', 'user1', '2024-05-01', 8),
    ('9788433999030', 'user2', '2024-05-01', 8),
    ('9788433999542', 'user1', '2024-05-01', 10),
    ('9788433999542', 'user3', '2024-05-01', 7),
    ('9788499309255', 'user2', '2024-05-01', 9),
    ('9788499309255', 'user3', '2024-05-01', 10),
    ('9788497937306', 'user4', '2024-05-01', 9);

insert into ressenya(isbn, username, comentari, data) values
    ('9788433915689', 'user1', 'Molt bon llibre!', '2024-05-01'),
    ('9788433915689', 'user2', 'Molt recomanable', '2024-05-01'),
    ('9788433915689', 'user3', 'Molt interessant', '2024-05-01'),
    ('9788433999030', 'user1', 'Bona novel·la', '2024-05-01'),
    ('9788433999030', 'user2', 'Molt recomanable', '2024-05-01'),
    ('9788433999542', 'user1', 'Molt bo', '2024-05-01'),
    ('9788433999542', 'user3', 'Interessant', '2024-05-01');

