create table llibre (
    isbn varchar(50) primary key,
    titol varchar(100) not null,
    resum text,
    nombre_pagines int unsigned,
    data_publicacio date not null,
    ruta_imatge varchar(200) not null
);

create table autor (
    id_autor int primary key,
    nom varchar(200) not null,
    data_naixement date not null
);

create table escriu (
    id_autor int,
    isbn varchar(50),
    primary key (id_autor, isbn),
    foreign key (id_autor) references autor (id_autor),
    foreign key (isbn) references llibre (isbn)
);

create table genere (
    id_genere int primary key,
    nom varchar(100) not null
);

create table llibre_genere (
    isbn varchar(50),
    id_genere int,
    primary key (isbn, id_genere),
    foreign key (isbn) references llibre (isbn),
    foreign key (id_genere) references genere (id_genere)
);

create table rol (
    id_rol int primary key,
    nom varchar(100) not null
);

create table usuari (
    id_usuari int primary key,
    id_rol int,
    username varchar(100) not null unique,
    correu_electronic varchar(100) not null,
    data_alta date not null,
    foreign key (id_rol) references rol (id_rol)
);

create table review (
    isbn varchar(50),
    id_usuari int,
    data_review date not null,
    data_lectura date not null,
    puntuacio int not null,
    comentari text,
    primary key (isbn, id_usuari),
    foreign key (isbn) references llibre (isbn),
    foreign key (id_usuari) references usuari (id_usuari)
);

create table llista (
    id_usuari_propietari int,
    id_llista int,
    nom varchar(100) not null,
    primary key (id_usuari_propietari, id_llista),
    foreign key (id_usuari_propietari) references usuari (id_usuari)
);

create table segueix_llista (
    id_usuari_propietari int,
    id_llista int,
    id_usuari int,
    primary key (id_usuari, id_usuari_propietari, id_llista),
    foreign key (id_usuari) references usuari (id_usuari),
    foreign key (id_usuari_propietari, id_llista) references llista (id_usuari_propietari, id_llista)
);
