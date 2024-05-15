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

create table user (
    username varchar(100) primary key,
    correu_electronic varchar(100) not null unique key,
    data_registre date not null,
    id_rol int,
    foreign key (id_rol) references rol (id_rol)
);

create table review (
    isbn varchar(50),
    username varchar(100),
    data_review date not null,
    data_lectura date not null,
    puntuacio int not null,
    comentari text,
    primary key (isbn, username),
    foreign key (isbn) references llibre (isbn),
    foreign key (username) references user (username)
);

create table llista (
    username_propietari varchar(100),
    id_llista int,
    nom varchar(100) not null,
    primary key (username_propietari, id_llista),
    foreign key (username_propietari) references user (username)
);

create table segueix_llista (
    username_propietari varchar(100),
    id_llista int,
    username_seguidor varchar(100),
    primary key (username_propietari, id_llista, username_seguidor),
    foreign key (username_seguidor) references user (username),
    foreign key (username_propietari, id_llista) references llista (username_propietari, id_llista)
);
