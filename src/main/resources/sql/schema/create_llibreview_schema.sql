drop table if exists segueix_llista;
drop table if exists llista;
drop table if exists rating;
drop table if exists user;
drop table if exists rol;
drop table if exists llibre_genere;
drop table if exists genere;
drop table if exists escriu;
drop table if exists autor;
drop table if exists llibre;

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
    nom_en varchar(100) not null,
    ruta_imatge varchar(200) not null
);

create table llibre_genere (
    isbn varchar(50),
    id_genere int,
    primary key (isbn, id_genere),
    foreign key (isbn) references llibre (isbn),
    foreign key (id_genere) references genere (id_genere)
);

create table user (
    username varchar(100) primary key,
    email varchar(100) not null unique,
    data_registre date not null
    -- id_rol int,
    -- foreign key (id_rol) references rol (id_rol)
);

create table rating (
    isbn varchar(50),
    username varchar(100),
    data_rating date not null,
    rating int not null,
    primary key (isbn, username),
    foreign key (isbn) references llibre (isbn),
    foreign key (username) references user (username)
);