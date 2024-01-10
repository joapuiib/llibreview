use llibreview;

insert into llibre (isbn, titol, resum, nombre_pagines, data_publicacio) values
    ("9788433915689", "Canto jo i la muntanya balla", "Canto jo i la muntanya balla és una novel·la d'Irene Solà publicada el 2019, de les més venudes a la literatura catalana contemporània, que ha arribat també al mercat internacional i ha estat traduïda a una vintena d''idiomes. El 2021 va ser la novel·la més prestada a la xarxa de biblioteques de Barcelona.", 162, "2019-05-8");

insert into autor(id_autor, nom, data_naixement) values
    (0, "Irene Solà", "1990-08-17");

insert into escriu(isbn, id_autor) values
    ("9788433915689", 0);