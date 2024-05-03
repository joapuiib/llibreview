package com.fpmislata.daw1.projectedaw1.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genere {
    private int id;
    private String nom;
    private String rutaImatge;

    public Genere(int id, String nom, String rutaImatge) {
        this.id = id;
        this.nom = nom;
        this.rutaImatge = rutaImatge;
    }

    public Genere() {
    }
}
