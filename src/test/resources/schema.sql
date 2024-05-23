create table llibre (
    isbn varchar(50) primary key,
    titol varchar(100) not null,
    resum text,
    data_publicacio date not null,
    nombre_pagines int unsigned,
    ruta_imatge varchar(200) not null
);

create table autor (
    id_autor int primary key,
    nom varchar(200) not null,
    biografia text not null,
    data_naixement date not null,
    ruta_imatge varchar(200) not null
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
    nom_ca varchar(100) not null,
    nom_en varchar(100) not null
);

create table llibre_genere (
    isbn varchar(50),
    id_genere int,
    primary key (isbn, id_genere),
    foreign key (isbn) references llibre (isbn),
    foreign key (id_genere) references genere (id_genere)
);

create table usuari (
    username varchar(100) primary key,
    email varchar(100) not null unique,
    data_registre date not null,
    password varchar(200) not null
    -- id_rol int,
    -- foreign key (id_rol) references rol (id_rol)
);

create table valoracio (
    isbn varchar(50),
    username varchar(100),
    data date not null,
    valoracio int not null,
    primary key (isbn, username),
    foreign key (isbn) references llibre (isbn),
    foreign key (username) references usuari (username)
);

create table ressenya (
    isbn varchar(50),
    username varchar(100),
    comentari text not null,
    data date not null,
    primary key (isbn, username),
    foreign key (isbn) references llibre (isbn),
    foreign key (username) references usuari (username),
    foreign key (isbn, username) references valoracio (isbn, username) on delete cascade
);
